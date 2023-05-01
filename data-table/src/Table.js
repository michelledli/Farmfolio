import React, { useMemo} from 'react'
import { usePagination, useTable } from 'react-table'
import MOCK_DATA from './MOCK_DATA.json'
import {COLUMNS} from './Columns'
 
function Table() {
  const columns = useMemo(() => COLUMNS, [])
  const data = useMemo(() => MOCK_DATA, [])
    
  const tableInstance = useTable({
      columns,
      data
  }, usePagination)

  const { 
    getTableProps, 
    getTableBodyProps, 
    headerGroups, 
    page, 
    nextPage,
    canNextPage, 
    previousPage, 
    canPreviousPage,
    pageCount,
    prepareRow,
    gotoPage,
  } = tableInstance

  return (
    <>
      <table {...getTableProps()}>
          <thead>
              {headerGroups.map((headerGroup) => (
                  <tr {...headerGroup.getHeaderGroupProps()}>
                      {headerGroup.headers.map((column) => (
                          <th {...column.getHeaderProps()}> 
                            {column.render('Header')}
                        </th>
                      ))}
                  </tr>
              ))}
          </thead>
          <tbody {...getTableBodyProps()}>
              {page.map(row => {
                  prepareRow(row)
                    return (
                      <tr {...row.getRowProps()}>
                        {row.cells.map((cell) => {
                          return <td {...cell.getCellProps()}>
                            {cell.render('Cell')}</td>
                              })}
                      </tr>
                      )
                  })
              }
          </tbody>
      </table>  
      
      <div className = 'pagination'>
      <button onClick={() => gotoPage(0)} disabled={!canPreviousPage}>
        {"<<"}
      </button>

      <button onClick={() => previousPage()}>Previous</button>
      <button onClick={() => nextPage()}>Next</button>

      <button onClick={() => gotoPage(pageCount - 1)} disabled={!canNextPage}>
        {">>"}
      </button>

      </div>
    </>    

  )
}
 
export default Table