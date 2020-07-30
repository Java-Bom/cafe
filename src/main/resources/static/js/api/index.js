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
};

export const api = (() => {
  const request = (uri, config) => fetch(uri, config);
  const requestWithJsonData = (uri, config) => fetch(uri, config).then(data => data.json());

  const table = {
    getAll() {
      return request(`/tables`);
    },

    get(id) {
      return request(`/tables/${id}`);
    },
    create(data) {
      return requestWithJsonData(`/tables`, METHOD.POST(data));
    },
    delete(id) {
      return request(`/tables/${id}`, METHOD.DELETE());
    }
  };

  const menu = {
    getAll() {
      return request(`/menus`);
    },
    getMenuTypes() {
      return request(`/menuType`);
    },
    get(id) {
      return requestWithJsonData(`/menus/${id}`);
    },
    create(data) {
      return requestWithJsonData(`/menus`, METHOD.POST(data));
    },
    delete(id) {
      return request(`/menus/${id}`, METHOD.DELETE());
    }
  };

  const order = {
    getAll(tableId) {
      return request(`/order/${tableId}`)
    },
    create(data) {
      return requestWithJsonData(`/order`, METHOD.POST(data))
    },
  };

  const payment = {
    payment(data) {
      return requestWithJsonData(`/payment`, METHOD.POST(data))
    },

    getAll() {
      return request("/sales")
    },

    getPaymentAmount(tableId) {
      return request(`/payment/${tableId}`)
    }
  };

  return {
    table,
    menu,
    order,
    payment,
  }
})();

export default api
