import Navigation from './Navigation'
import Footer from './Footer'

const Layout = ({ children }) => {
  return (
    <div className="page">
      <Navigation />
      {children ? <main>{children}</main> : null}
      <Footer />
    </div>
  )
}

export default Layout
