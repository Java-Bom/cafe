import {initSales} from "./utils/templates.js";
import {mockSales} from "./utils/mockData.js";
import {api} from "./api/index.js"

function SalesApp() {

    const initSalesList = () => {
        //TODO api call 로 mock 데이터를 실제 데이터로 바꾸기
        api.payment
            .getAll()
            .then(response => response.json())
            .then(data => initSales(data));
    };

    const init = () => {
        initSalesList();
    };

    return {
        init
    };
}

const salesApp = new SalesApp();
salesApp.init();
