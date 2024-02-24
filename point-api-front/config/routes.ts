export default [
  {
    path: '/user',
    layout: false,
    routes: [
      {path: '/user/login', component: './User/Login'},
      {path: '/user/register', component: './User/Register'},
    ],
  },
  // { path: '/welcome', icon: 'smile', component: './Welcome', name: '欢迎页' },
  {path: '/', icon: 'home', component: './Index', name: '主页'},
  {
    path: '/interfaceinfo/detail/:id',
    icon: 'home',
    component: './InterfaceInfo/Detail',
    name: '接口详情',
    hideInMenu: true,
  },
  {
    path: '/admin',
    icon: 'crown',
    name: '管理页',
    access: 'canAdmin',
    routes: [
      {
        icon: 'table', path: '/admin/user', component: './Admin/User', name: '用户管理'
      },
      {
        icon: 'table', path: '/admin/interfaceinfo', component: './Admin/InterfaceInfo', name: '接口管理'
      },
      {
        icon: 'table', path: '/admin/userinterfaceinfo', component: './Admin/UserInterfaceInfo', name: '用户接口管理'
      },
    ],
  },
  // { path: '/', redirect: '/welcome' },
  {path: '*', layout: false, component: './404'},
];
