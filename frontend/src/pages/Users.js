import { useEffect, useState } from "react";
import { FrontendAPI } from "../api";

export function Users() {
  const [list, setList] = useState(null);

  useEffect(() => {
    (async () => {
      const r = await FrontendAPI.getAccounts();
      setList(r);
    })();
  }, []);

  if (!list) return "";

  const table = (data, keystr, deleteUser) => {
    return (
      <div className="users__user">
        <button onClick={deleteUser} className="users__delete">
          Delete
        </button>
        <table key={keystr}>
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
      </div>
    );
  };

  const markup = list.map((e, index) => {
    const deleteUser = async () => {
      await FrontendAPI.deleteAccount(e.id);
      window.location.reload();
    };

    return (
      <>
        {table(e, index, deleteUser)}
        {index !== list.length - 1 ? <hr></hr> : null}
      </>
    );
  });

  return (
    <>
      <div className="page__header">Users</div>

      <div className="users">
        <div
          className="post post__create"
          onClick={(e) => (window.location = "/user-add")}
        >
          +
        </div>
        {markup}
      </div>
    </>
  );
}

export default Users;
