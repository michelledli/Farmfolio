import { useState, useEffect } from "react";
import axios from "axios";

function AuditEntry(entry, key) {
  console.log(entry);
  let type = entry?.type;
  let before = JSON.parse(entry.before);
  let after = JSON.parse(entry.after);

  const output = !before.id
    ? after
    : Object.keys(before).reduce((diff, key) => {
        if (before[key] !== after[key]) diff[key] = [before[key], after[key]];
        return diff;
      }, {});

  const table = (data, heading, keystr) => {
    console.log(keystr);

    return (
      <table key={keystr}>
        <tbody key={keystr + "-tbody"}>
          {heading ? heading : null}
          {Object.keys(data).map((key, index) => {
            let cell = data[key];
            let rowstr = keystr + "-" + key + "-" + index;
            return (
              <tr key={rowstr + "-tr"}>
                <th key={rowstr + "-th"}>{key}</th>
                <td key={rowstr + "-td"}>
                  {cell &&
                  !(typeof cell == "string") &&
                  !(typeof cell == "number") &&
                  !(typeof cell == "boolean")
                    ? table(cell, null, rowstr)
                    : (cell || "-").toString()}
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    );
  };

  const keyPrefix = `${type}-${key}`;
  
  const heading = type ? (
    <tr key={keyPrefix + "-heading"}>
      <th key={keyPrefix + "-heading-type"}>
        {type} {before?.id ? "Updated" : "Created"}
      </th>
      <th key={keyPrefix + "-heading-date"}>
        {new Date(entry?.time).toLocaleString()}
      </th>
    </tr>
  ) : (
    ""
  );

  return table(output, heading, keyPrefix);
}

function AuditLog() {
  const [log, setLog] = useState(null);

  useEffect(() => {
    axios
      .get(`/api/audit`)
      .then((response) => {
        setLog(response.data);
      })
      .catch((error) => {
        console.error("API Request Error:", error);
      });
  }, []);

  if (!log) return "";

  const markup = log.map((e, index) => {
    let entry = AuditEntry(e, index);
    return entry;
  });

  return <div className="audit">{markup}</div>;
}

export default AuditLog;
