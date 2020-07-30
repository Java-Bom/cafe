import {convertOrderItemTemplate, initOrderMenuOption, initPosTables} from "./utils/templates.js";
import {mockMenus, mockOrder, mockPosTable} from "./utils/mockData.js";
import {EVENT_TYPE} from "./utils/constants.js";
import Modal from './ui/Modal.js'
import {api} from './api/index.js'

function PosApp() {
    const $tableContainer = document.querySelector('.table-container');
    const $orderBtn = document.querySelector("#order-submit-button");
    const $cardPaymentBtn = document.querySelector("#card-submit-button");
    const $cashPaymentBtn = document.querySelector("#cash-submit-button");

    let menuModal;
    let currentTableId;

    const onShowOrderHandler = event => {
        convertOrderItemTemplate();
        setOrderPrice(0, 0);
        const $target = event.target;
        const $table = $target.closest('.table');
        const isModalOpen = $target.classList.contains('modal-open');
        if (!isModalOpen) {
            return
        }

        const tableId = $table.dataset.id;
        currentTableId = tableId;

        //TODO tableId 로 해당 테이블 주문 정보 api call
        api.order
            .getAll(tableId)
            .then(response => response.json())
            .then(data => {
                const $orderTableName = document.querySelector('#order-table-name');
                $orderTableName.innerHTML = data.tableName;
                convertOrderItemTemplate(data.orders);
            });

        api.payment
            .getPaymentAmount(currentTableId)
            .then(response => response.json())
            .then(data => setOrderPrice(data.cashPrice, data.cardPrice));

    };

    const onCreateOrderItemHandler = () => {
        const $orderMenuId = document.querySelector("#menu-select-options");
        const $orderAmount = document.querySelector("#menu-amount-select");

        const newOrder = {
            'tableId' : currentTableId,
            'orderMenuId' : $orderMenuId.value,
            'orderAmount' : $orderAmount.value,
        };

        api.order.create(newOrder);

    };

    const onCreateCashPayment = () => {
        const $tableName = document.querySelector("#order-table-name");

        const paymentAmount = {
            'tableId' : currentTableId,
            'tableName' : $tableName.value,
            'paymentType' : 'CASH',
        };

        api.payment
            .payment(paymentAmount);
    };

    const onCreateCardPayment = () => {
        const $tableName = document.querySelector("#order-table-name");

        const paymentAmount = {
            'tableId' : currentTableId,
            'tableName' : $tableName.value,
            'paymentType' : 'CARD',
        };

        api.payment
            .payment(paymentAmount);
    };

    const setOrderPrice = (cash, card) => {
        const $cashPrice = document.querySelector('#cash-price');
        const $cardPrice = document.querySelector('#card-price');
        $cardPrice.innerHTML = card + ' 원';
        $cashPrice.innerHTML = cash + ' 원';
    };

    const initMenuSelectOptions = () => {
        //TODO api call 로 mock 데이터를 실제 데이터로 바꾸기
        // const menuResponse = mockMenus;
        api.menu.getAll()
            .then(response => response.json())
            .then(data => initOrderMenuOption(data));
        // initOrderMenuOption(mockMenus)
    };

    const initEventListeners = () => {
        $tableContainer.addEventListener(EVENT_TYPE.CLICK, onShowOrderHandler);
        $orderBtn.addEventListener(EVENT_TYPE.CLICK, onCreateOrderItemHandler);
        $cardPaymentBtn.addEventListener(EVENT_TYPE.CLICK, onCreateCardPayment);
        $cashPaymentBtn.addEventListener(EVENT_TYPE.CLICK, onCreateCashPayment);
    };

    const initTables = () => {
        //TODO api call 로 mock 데이터를 실제 데이터로 바꾸기
        api.table.getAll()
            .then(response => response.json())
            .then(data => initPosTables(data));
        // initPosTables(mockPosTable);
    };

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
