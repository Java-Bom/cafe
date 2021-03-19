const METHOD = {
    GET() {
        return {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }
    },
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
    const requestWithJsonData = (uri, config) => fetch(uri, config).then(data => {
        console.log(data)
        return data.json()
    })

    const table = {
        getAll() {
            //Failed to load resource: the server responded with a status of 404 () 대체 원인이 무어실까 ㅅㅂ 족같누
            return request(`cafe/tables`, METHOD.GET())
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
})

export default api
