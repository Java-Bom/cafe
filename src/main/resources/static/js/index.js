import {convertOrderItemTemplate, initOrderMenuOption, initPosTables} from "./utils/templates.js";
import {mockMenus} from "./utils/mockData.js";
import {EVENT_TYPE} from "./utils/constants.js";
import Modal from './ui/Modal.js'
import api from './api/index.js'

function PosApp() {
    const $tableContainer = document.querySelector('.table-container')

    let menuModal;

    const onShowOrderHandler = event => {
        convertOrderItemTemplate()
        setOrderPrice(0, 0)
        const $target = event.target
        const $table = $target.closest('.table')
        const isModalOpen = $target.classList.contains('modal-open')
        if (!isModalOpen) {
            return
        }

        const tableId = $table.dataset.id
        //TODO tableId 로 해당 테이블 주문 정보 api call
        api.order.get(tableId)
            .then(response => response.json())
            .then(function (data) {
                const orderResponse = data;
                const $orderTableName = document.querySelector('#order-table-name')

                $orderTableName.innerHTML = orderResponse.tableName;
                convertOrderItemTemplate(orderResponse.orders);
                setOrderPrice(0, 0);
            });
    }

    const setOrderPrice = (cash, card) => {
        const $cashPrice = document.querySelector('#cash-price')
        const $cardPrice = document.querySelector('#card-price')
        $cardPrice.innerHTML = cash + ' 원'
        $cashPrice.innerHTML = card + ' 원'
    }

    const initMenuSelectOptions = () => {
        //TODO api call 로 mock 데이터를 실제 데이터로 바꾸기
        const menuResponse = mockMenus;
        initOrderMenuOption(menuResponse)
    }

    const initEventListeners = () => {
        $tableContainer.addEventListener(EVENT_TYPE.CLICK, onShowOrderHandler)
    }

    const initTables = () => {
        //TODO api call 로 mock 데이터를 실제 데이터로 바꾸기
        const tableResponse = api.table.getAll()
            .then(response => response.json())
            .then(data => initPosTables(data))
    }

    const init = () => {
        initTables();
        menuModal = new Modal();
        initMenuSelectOptions();
        initEventListeners();
    };

    return {
        init
    };
}

const posApp = new PosApp();
posApp.init();
