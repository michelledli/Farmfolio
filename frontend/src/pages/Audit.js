import { useState, useEffect } from "react";
import { FrontendAPI } from "../api";

function AuditEntry(entry, key) {
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
    return (
      <>
        <table key={keystr}>
          <thead> {heading ? heading : null}</thead>
          <tbody key={keystr + "-tbody"}>
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
      </>
    );
  };

  const keyPrefix = `${type}-${key}`;

  const heading = type ? (
    <tr key={keyPrefix + "-heading"}>
      <th key={keyPrefix + "-heading-type"}>
        {type} {before?.id ? "Updated" : "Created"} - User{" "}
        {entry?.accountId || "[undefined]"}
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
    (async () => {
      const r = await FrontendAPI.getAudit();
      setLog(r);
    })();

  }, []);

  if (!log) return "";

  const markup = log.map((e, index) => {
    let entry = AuditEntry(e, index);
    return entry;
  });

  return <div className="audit">{markup}</div>;
}

export function Audit() {
  return (
    <>
      <div className="page__header">Audit Log</div>
      {AuditLog()}
    </>
  );
}

export default Audit;
