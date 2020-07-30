import {initNavigation} from "./utils/templates.js";

function PosApp() {
    const init = () => {
        initNavigation();
    };

    return {
        init
    };
}

const posApp = new PosApp();
posApp.init();