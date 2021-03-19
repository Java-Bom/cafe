import {initMenu, initMenuCategory} from "./utils/templates.js";
import {mockMenuCategory, mockMenus} from "./utils/mockData.js";
import {EVENT_TYPE} from './utils/constants.js'


function MenuApp() {
    const $menuList = document.querySelector('#menu-list')
    const $menuAddBtn = document.querySelector('#menu-add-btn')
    const $menuNameInput = document.querySelector('#menu-name')
    const $menuPriceInput = document.querySelector('#menu-price')
    const type = document.getElementById("menu-type-select")
    const selectedIndex = type.selectedIndex
    const typeText = type.options[selectedIndex].text
    const typeValue = type.options[selectedIndex].value

    const onCreateMenuItemHandler = () => {
        const newMenuItem = {
            'menuName': $menuNameInput.value,
            'menuPrice': $menuPriceInput.value,
            'menuType': typeValue,
        }
        //TODO 생성 api 호출
    }

    const onDeleteMenuItemHandler = event => {
        const $target = event.target
        const $menuItem = $target.closest('.menu-item')
        const isDeleteButton = $target.classList.contains('mdi-delete')
        if (!isDeleteButton) {
            return
        }
        const menuId = $menuItem.dataset.id
        //TODO 삭제 api 호출

    }

    const initEventListeners = () => {
        $menuList.addEventListener(EVENT_TYPE.CLICK, onDeleteMenuItemHandler)
        $menuAddBtn.addEventListener(EVENT_TYPE.CLICK, onCreateMenuItemHandler)
    }

    const initTables = () => {
        //TODO api call 로 mock 데이터를 실제 데이터로 바꾸기
        initMenu(mockMenus);
        initMenuCategory(mockMenuCategory);
        initEventListeners();
    }

    const init = () => {
        initTables();
    };

    return {
        init
    };
}

const menuApp = new MenuApp();
menuApp.init();
