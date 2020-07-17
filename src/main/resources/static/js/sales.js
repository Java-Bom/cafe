import {EVENT_TYPE} from './utils/constants.js'
import {initSales} from "./utils/templates.js";
import {mockSales} from "./utils/mockData.js";


function TableApp() {
    const $tableList = document.querySelector('#table-list')

    const initEventListeners = () => {
        $tableList.addEventListener(EVENT_TYPE.CLICK, onDeleteTableItemHandler)
    }

    const initSalesList = () => {
        //TODO api call 로 mock 데이터를 실제 데이터로 바꾸기
        initSales(mockSales);
        initEventListeners();
    }

    const init = () => {
        initSalesList();
    };

    return {
        init
    };
}

const tableApp = new TableApp();
tableApp.init();
