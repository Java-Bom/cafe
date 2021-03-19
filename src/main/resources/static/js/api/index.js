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
        return request(`/tables`)
    },
    get(id) {
      return request(`/tables/${id}`)
    },
    create(data) {
      return requestWithJsonData(`/tables`, METHOD.POST(data))
    },
    delete(id) {
      return request(`/tables/${id}`, METHOD.DELETE())
    }
  }

  const menu = {
    getAll() {
      return request(`/menus`)
    },
    get(id) {
      return requestWithJsonData(`/menus/${id}`)
    },
    create(data) {
      return requestWithJsonData(`/menus`, METHOD.POST(data))
    },
    delete(id) {
      return request(`/menus/${id}`, METHOD.DELETE())
    },
    getCategoryAll() {
      return request(`/menus/categories`)
    }
  }

  const order = {
    get(id) {
      return request(`/orders/${id}`)
    },

    create(data) {
      return request(`/orders`, METHOD.POST(data))
    }
  }

  return {
    table,
    menu,
    order
  }
})()

export default api
