import {convertOrderItemTemplate, initOrderMenuOption, initPosTables} from "./utils/templates.js";
import {EVENT_TYPE} from "./utils/constants.js";
import Modal from './ui/Modal.js'
import api from './api/index.js'

function PosApp() {
    const $tableContainer = document.querySelector('.table-container')
    const $orderAddBtn = document.querySelector('#order-submit-button')

    let menuModal;
    let currentTableId;

    const onShowOrderHandler = event => {
        convertOrderItemTemplate()
        setOrderPrice(0, 0)
        const $target = event.target
        const $table = $target.closest('.table')
        const isModalOpen = $target.classList.contains('modal-open')
        if (!isModalOpen) {
            return
        }

        const tableId = $table.dataset.id;
        currentTableId = tableId;

        //TODO tableId 로 해당 테이블 주문 정보 api call
        api.order.get(tableId)
            .then(response => response.json())
            .then(function (data) {
                const orderResponse = data;
                const $orderTableName = document.querySelector('#order-table-name')

                $orderTableName.innerHTML = orderResponse.tableName;
                convertOrderItemTemplate(orderResponse.orders);
                setOrderPrice(orderResponse.payment.cash, orderResponse.payment.card);
            });
    }

    const setOrderPrice = (cash, card) => {
        const $cashPrice = document.querySelector('#cash-price')
        const $cardPrice = document.querySelector('#card-price')
        $cardPrice.innerHTML = card + ' 원'
        $cashPrice.innerHTML = cash + ' 원'
    }

    const initMenuSelectOptions = () => {
        //TODO api call 로 mock 데이터를 실제 데이터로 바꾸기
        api.menu.getAll()
            .then(response => response.json())
            .then(data => initOrderMenuOption(data));
    }

    const onCreateOrderHandler = () => {
        const $menuSelect = document.querySelector('#menu-select-options');
        const $menuQuantitySelect = document.querySelector('#menu-amount-select')

        const order = {
            'tableId': currentTableId,
            'menuId': $menuSelect.value,
            'quantity': $menuQuantitySelect.value
        };

        console.log(order);

        api.order.create(order);
    }

    const initEventListeners = () => {
        $tableContainer.addEventListener(EVENT_TYPE.CLICK, onShowOrderHandler)
        $orderAddBtn.addEventListener(EVENT_TYPE.CLICK, onCreateOrderHandler);
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
