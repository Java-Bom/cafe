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
    get(id) {
      return request(`/tables/${id}`)
    },
    create(data) {
      return requestWithJsonData(`/tables`, METHOD.POST(data))
    },
    delete(id) {
      return request(`/tables/${id}`, METHOD.DELETE())
    },
    findAll() {
      return request(`/tables`)
    },
    getAmount(id) {
      return request(`/tables/${id}/amounts`)
    }
  }

  const menu = {
    get(id) {
      return requestWithJsonData(`/menus/${id}`)
    },
    create(data) {
      return requestWithJsonData(`/menus`, METHOD.POST(data))
    },
    delete(id) {
      return request(`/menus/${id}`, METHOD.DELETE())
    },
    findAll() {
      return request(`/menus`)
    },
    findAllType() {
      return request(`/menus/type`)
    }
  }

  const order = {
    get(id) {
      return requestWithJsonData(`/orders/${id}`)
    },
  }

  return {
    table,
    menu,
    order
  }
})()

export default api
