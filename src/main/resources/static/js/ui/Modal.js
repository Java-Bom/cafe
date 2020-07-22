import {EVENT_TYPE} from "../utils/constants.js";

export default function Modal() {

    const $modalContainer = document.querySelector(".modal-container");
    const $closeModalButton = document.querySelector(".modal-close");
    const $body = document.querySelector("body");
    const $modal = document.querySelector(".modal");

    const toggle = event => {
        if (event) {
            event.preventDefault();
        }
        $body.classList.toggle("modal-active");
        $modal.classList.toggle("opacity-0");
        $modal.classList.toggle("pointer-events-none");
    };

    const openModal = event => {
        const $target = event.target
        const isModalOpen = $target.classList.contains('modal-open')
        if (!isModalOpen) {
            return
        }
        toggle(event);
    }

    $modalContainer.addEventListener(EVENT_TYPE.CLICK, openModal)
    $closeModalButton.addEventListener(EVENT_TYPE.CLICK, toggle);

    return {
        toggle
    };
}
