import {DEFAULT_LAYOUT} from '../base';
import {AppRouteRecordRaw} from '../types';

const dev: AppRouteRecordRaw = {
  path: '/device',
  name: 'device',
  component: DEFAULT_LAYOUT,
  meta: {
    locale: '硬件管理',
    requiresAuth: true,
    icon: 'icon-list',
    order: 2,
  },
  children: [
    {
      path: 'type', // The midline path complies with SEO specifications
      name: 'type',
      component: () => import('@/views/device/type/index.vue'),
      meta: {
        locale: "类型管理",
        requiresAuth: true,
        roles: ['*'],
      },
    },
    {
      path: 'info',
      name: 'info',
      component: () => import('@/views/device/info/index.vue'),
      meta: {
        locale: '硬件信息管理',
        requiresAuth: true,
        roles: ['*'],
      },
    }, {
      path: 'mapping',
      name: 'mapping',
      component: () => import('@/views/device/mapping/index.vue'),
      meta: {
        locale: '硬件映射管理',
        requiresAuth: true,
        roles: ['*'],
      },
    }
  ],
};

export default dev;
