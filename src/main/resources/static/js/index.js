import {convertOrderItemTemplate, initOrderMenuOption, initPosTables} from "./utils/templates.js";
import {mockMenus, mockOrder, mockPosTable} from "./utils/mockData.js";
import {EVENT_TYPE} from "./utils/constants.js";
import api from "./api/index"
import Modal from './ui/Modal.js'

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
        api.table
            .get(tableId)
            .then(res => res.body)
            .then(table => {
                const $orderTableName = document.querySelector('#order-table-name')
                $orderTableName.innerHTML = table.name;
                convertOrderItemTemplate(table.orders);
            })

        api.table
            .getAmount(tableId)
            .then(res => res.body)
            .then(table => setOrderPrice(table.cashPrice, table.cardPrice))
    }

    const setOrderPrice = (cash, card) => {
        const $cashPrice = document.querySelector('#cash-price')
        const $cardPrice = document.querySelector('#card-price')
        $cardPrice.innerHTML = cash + ' 원'
        $cashPrice.innerHTML = card + ' 원'
    }

    const initMenuSelectOptions = () => {
        //TODO api call 로 mock 데이터를 실제 데이터로 바꾸기
        api.menu.findAll()
            .then(res => res.body)
            .then(initOrderMenuOption);
    }

    const initEventListeners = () => {
        $tableContainer.addEventListener(EVENT_TYPE.CLICK, onShowOrderHandler)
    }

    const initTables = () => {
        //TODO api call 로 mock 데이터를 실제 데이터로 바꾸기
        api.table.findAll()
            .then(res => res.body)
            .then(initPosTables);
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
