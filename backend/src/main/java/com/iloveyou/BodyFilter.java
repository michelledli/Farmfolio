package com.iloveyou;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.jsonwebtoken.Claims;

@Component
public class BodyFilter extends OncePerRequestFilter {
	private final Collection<String> exclude = List.of("/api/images/**", "/h2-console");

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		AntPathMatcher pathMatcher = new AntPathMatcher();
		boolean filter = exclude.stream()
				.anyMatch(p -> pathMatcher.match(p, request.getServletPath()));

		return filter;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		chain.doFilter(new RequestWrapper(request), response);
	}

	private class RequestWrapper extends HttpServletRequestWrapper {

		private byte[] cachedBody;

		public RequestWrapper(HttpServletRequest request) throws IOException {
			super(request);

			InputStream requestInputStream = request.getInputStream();
			this.cachedBody = StreamUtils.copyToByteArray(requestInputStream);

			if (cachedBody.length == 0)
				return;

			Claims claims = (Claims) request.getAttribute("claims");
			Long auditId = 1L;
			if (claims != null)
				auditId = Long.valueOf(claims.getId());

			String contentType = request.getHeader("Content-Type");

			if (contentType.contains("application/json")) {
				ObjectMapper json = new ObjectMapper();
				JsonNode node = json.readTree(this.cachedBody);
				((ObjectNode) node).put("auditId", auditId);
				this.cachedBody = json.writeValueAsBytes(node);
			}
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			return new BodyStream(this.cachedBody);
		}

		@Override
		public BufferedReader getReader() throws IOException {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedBody);
			return new BufferedReader(new InputStreamReader(byteArrayInputStream));
		}
	}

	private class BodyStream extends ServletInputStream {
		private InputStream cachedBodyInputStream;

		public BodyStream(byte[] cachedBody) {
			this.cachedBodyInputStream = new ByteArrayInputStream(cachedBody);
		}

		@Override
		public boolean isFinished() {
			try {
				return cachedBodyInputStream.available() == 0;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setReadListener(ReadListener readListener) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int read() throws IOException {
			return cachedBodyInputStream.read();
		}
	}
}