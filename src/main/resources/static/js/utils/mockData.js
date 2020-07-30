export const mockPosTable = [
    {
        'tableId': 1,
        'tableName': '1번 테이블',
        'orderStatus': true,
    },
    {
        'tableId': 2,
        'tableName': '2번 테이블',
        'orderStatus': false,
    },
    {
        'tableId': 3,
        'tableName': '3번 테이블',
        'orderStatus': true,
    },
    {
        'tableId': 4,
        'tableName': '4번 테이블',
        'orderStatus': true,
    }
];

export const mockOrder =
    {
        'tableId': 1,
        'tableName': '1번 테이블',
        'orders': [
            {
                'menuName': '카페라떼',
                'orderAmount': 2,
                'price': 10000
            },
            {
                'menuName': '당근 케이크',
                'orderAmount': 1,
                'price': 6500
            },
            {
                'menuName': '티라미수',
                'orderAmount': 1,
                'price': 7000
            }
        ]
    };

export const mockMenus = [
    {
        'menuId': 1,
        'menuName': '아메리카노',
        'price': 4500,
        'type': 'BEVERAGE'
    },
    {
        'menuId': 2,
        'menuName': '카페라떼',
        'price': 5000,
        'type': 'BEVERAGE'
    },
    {
        'menuId': 3,
        'menuName': '당근 케이크',
        'price': 6500,
        'type': 'CAKE'
    }
];

export const mockMenuCategory = [
    {
        'categoryId': 1,
        'name': 'CAKE'
    },
    {
        'categoryId': 2,
        'name': 'BEVERAGE'
    }
];

export const mockTable = [
    {
        'tableId': 1,
        'tableName': '1번 테이블'
    },
    {
        'tableId': 2,
        'tableName': '2번 테이블'
    }
];

export const mockSales = [
    {
        'salesId': 1,
        'tableName': '1번 테이블',
        'price': 70000,
        'date': '2020-06-14 10:22:34'
    },
    {
        'salesId': 2,
        'tableName': '1번 테이블',
        'price': 33000,
        'date': '2020-06-14 11:22:34'
    },
    {
        'salesId': 3,
        'tableName': '2번 테이블',
        'price': 140000,
        'date': '2020-06-14 11:32:34'
    },
];