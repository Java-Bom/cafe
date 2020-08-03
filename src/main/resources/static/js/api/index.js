const METHOD = {
    PUT(data) {
        return {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                ...data
            })
        }
    },
    DELETE() {
        return {
            method: 'DELETE'
        }
    },
    POST(data) {
        return {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                ...data
            })
        }
    }
}

const api = (() => {
    const request = (uri, config) => fetch(uri, config)
    const requestWithJsonData = (uri, config) => fetch(uri, config).then(data => data.json())

    const table = {
        getAll() {
            return request(`/cafe/tables`)
        },
        get(id) {
            return request(`/cafe/tables/${id}`)
        },
        create(data) {
            return requestWithJsonData(`/cafe/tables`, METHOD.POST(data))
        },
        delete(id) {
            return request(`/cafe/tables/${id}`, METHOD.DELETE())
        }
    }

    const menu = {
        get(id) {
            return requestWithJsonData(`/cafe/menus/${id}`)
        },
        create(data) {
            return requestWithJsonData(`/cafe/menus`, METHOD.POST(data))
        },
        delete(id) {
            return request(`/cafe/menus/${id}`, METHOD.DELETE())
        }
    }

    return {
        table,
        menu
    }
})()

export default api
