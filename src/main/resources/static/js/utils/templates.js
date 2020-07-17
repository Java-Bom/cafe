export const initNavigation = () => {
    document.querySelector('body').insertAdjacentHTML('afterBegin', navTemplate)
}

const navTemplate = `<nav class="flex items-center justify-between flex-wrap bg-blue-800 p-4">
  <div class="flex items-center flex-shrink-0 text-gray-800 w-full">
      <a href="/" class="mr-2">
        <img src="img/pos-terminal.png" class="w-6">
      </a>
    <div class="flex justify-start">
      <div class="hover:bg-blue-900 px-2 py-1 rounded">
         <a href="/menus" class="block inline-block lg:mt-0 text-white text-sm">
          메뉴 관리
          </a>
      </div>
      <div class="hover:bg-blue-900 px-2 py-1 rounded">
         <a href="/tables" class="block inline-block lg:mt-0 text-white text-sm">
          테이블 관리
          </a>
      </div>
      <div class="hover:bg-blue-900 px-2 py-1 rounded">
          <a href="/sales" class="block inline-block lg:mt-0 text-white text-sm">
          매출 관리
          </a>
      </div>
    </div>
</nav>`

export const initPosTables = (data) => {
    const tableItems = data ? data.map(table => posTableTemplate(table)).join('') : null
    document.querySelector('.table-container').insertAdjacentHTML('afterBegin', tableItems)
}

const posTableTemplate = (data) => {
    const orderStatus = data.orderStatus ? '$$$$' : ''
    return `<div class="table border-1 round p-3 " data-id="${data.tableId}">
                <div class="table-name modal-open bg-transparent hover:bg-blue-300">${data.tableName}</div>
                <div class="order-container">
                    ${orderStatus}
                </div>
            </div>`
}

export const initMenu = (data) => {
    const menuItems = data ? data.map(menu => menuTemplate(menu)).join('') : null
    document.querySelector('#menu-list').insertAdjacentHTML('afterBegin', menuItems)
}

const menuTemplate = (data) => {
    return `<div class="menu-item border border-gray-200 py-2 px-4 text-gray-800" data-id="${data.menuId}">
                                <div class="menu-name float-left w-50">${data.menuName}</div>
                                <div class="menu-price float-left pl-10">${data.price} 원</div>
                                &nbsp
                                <button class="hover:bg-gray-300 hover:text-gray-500 text-gray-300 px-1 rounded-full float-right">
                                    <span class="mdi mdi-delete"></span>
                                </button>
                            </div>`
}

export const initTable = (data) => {
    const tableItems = data ? data.map(table => tableTemplate(table)).join('') : null
    document.querySelector('#table-list').insertAdjacentHTML('afterBegin', tableItems)
}

const tableTemplate = (data) => {
    return `<div class="table-item border border-gray-200 py-2 px-4 text-gray-800" data-id="${data.tableId}">
                                <div class="table-name float-left w-50">${data.tableName}</div>
                                &nbsp
                                <button class="hover:bg-gray-300 hover:text-gray-500 text-gray-300 px-1 rounded-full float-right">
                                    <span class="mdi mdi-delete"></span>
                                </button>
                            </div>`
}

export const initSales = (data) => {
    const salesItems = data ? data.map(sale => salesTemplate(sale)).join('') : null
    document.querySelector('#sales-list').insertAdjacentHTML('afterBegin', salesItems)
}

const salesTemplate = (data) => {
    return `<div class="sales-item border border-gray-200 py-2 px-4 text-gray-800" data-id="${data.salesId}">
                                <div class="sales-Id float-left">${data.salesId}:</div>
                                <div class="sales-table ml-1 float-left">${data.tableName}</div>
                                <div class="sales-data float-right pl-3">${data.date}</div>
                                <div class="sales-price float-right pl-3">${data.price}</div>
                                &nbsp
                            </div>`
}


export const convertOrderItemTemplate = (data) => {
    const orderItems = data ? data.map(order => orderItemTemplate(order)).join('') : null
    if (orderItems) {
        document.querySelector('#order-list').insertAdjacentHTML('afterBegin', orderItems)
        return
    }
    document.querySelector('#order-list').innerHTML = ''

}

const orderItemTemplate = (data) => {
    return `<div class="order-item border border-gray-200 py-2 px-4 text-gray-800" data-id="${data.menuId}">
                <div class="order-name float-left w-50">${data.menuName}</div>
                <div class="order-amount float-left pl-10">${data.orderAmount}</div>
                <div class="order-total-price float-left pl-10">${data.price}</div>
                <span>원</span>
            </div>`
}

export const initOrderMenuOption = (data) => {
    const menuOptions = data ? data.map(menu => orderMenuOptionTemplate(menu)).join('') : null
    document.querySelector('#menu-select-options').insertAdjacentHTML('afterBegin', menuOptions)
}


export const orderMenuOptionTemplate = data => `<option value="${data.menuId}">${data.menuName}</option>`
