import {initMenu, initMenuCategory} from "./utils/templates.js";
import {EVENT_TYPE} from './utils/constants.js'
import {api} from "./api/index.js"

function MenuApp() {
    const $menuList = document.querySelector('#menu-list');
    const $menuAddBtn = document.querySelector('#menu-add-btn');
    const $menuNameInput = document.querySelector('#menu-name');
    const $menuPriceInput = document.querySelector('#menu-price');
    const $menuTypeSelector = document.querySelector("#menu-type-select");

    const onCreateMenuItemHandler = () => {
        const newMenuItem = {
            'menuName': $menuNameInput.value,
            'menuPrice': $menuPriceInput.value,
            "menuType": $menuTypeSelector.value
        };

        //TODO 생성 api 호출
        api.menu.create(newMenuItem)
            .then(data => console.log(JSON.stringify(data)));
        window.location.reload()
    };

    const onDeleteMenuItemHandler = event => {
        const $target = event.target;
        const $menuItem = $target.closest('.menu-item');
        const isDeleteButton = $target.classList.contains('mdi-delete');

        if (!isDeleteButton) {
            return
        }
        const menuId = $menuItem.dataset.id;

        //TODO 삭제 api 호출
        api.menu.delete(menuId);
        window.location.reload()
    };

    const initEventListeners = () => {
        $menuList.addEventListener(EVENT_TYPE.CLICK, onDeleteMenuItemHandler);
        $menuAddBtn.addEventListener(EVENT_TYPE.CLICK, onCreateMenuItemHandler);
    };

    const initTables = () => {
        //TODO api call 로 mock 데이터를 실제 데이터로 바꾸기

        api.menu.getAll()
            .then(response => response.json())
            .then(data => initMenu(data));

        api.menu.getMenuTypes()
            .then(response => response.json())
            .then(data => initMenuCategory(data));

        initEventListeners();
    };


    const init = () => {
        initTables();
    };

    return {
        init
    };
}

const menuApp = new MenuApp();
menuApp.init();
