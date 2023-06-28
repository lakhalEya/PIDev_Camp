import { INavData } from '@coreui/angular';

export const navItems: INavData[] = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    iconComponent: { name: 'cil-speedometer' },
  },
  {
    title: true,
    name: 'Users',
  },

  {
    name: 'User Management',
    url: '/dashboard/users/user-management', //
    iconComponent: { name: 'cil-user' },
  },
  {
    name: 'Reclamation',
    url: '/dashboard/users/reclamations', //
    iconComponent: { name: 'cil-mood-bad' },
  },

  {
    title: true,
    name: 'parcs'
  },
  {
    name: 'Parc',
    url: '/dashboard/parcs/parc', //
    iconComponent: { name: 'cil-location-pin' },
  },
  {
    name: 'Equipment',
    url: '/dashboard/parcs/equipment', //
    iconComponent: { name: 'cil-location-pin' },
  },
  {
    name: 'Activity',
    url: '/dashboard/parcs/activity', //
    iconComponent: { name: 'cil-location-pin' },
  },
  {
    title: true,
    name: 'Social'
  },
  {
    name: 'Forum',
    url: '/dashboard/social/forums', //
    iconComponent: { name: 'cil-chat-bubble' },
  },
  {
    name: 'Post',
    url: '/dashboard/social/posts', //
    iconComponent: { name: 'cil-chat-bubble' },
  },
  {
    name: 'Reaction',
    url: '/dashboard/social/reactions', //
    iconComponent: { name: 'cil-chat-bubble' },
  },
  {
    title: true,
    name: 'E-commerce'
  },
  {
    name: 'Product',
    url: '/dashboard/e-commerce/products', //
    iconComponent: { name: 'cil-list' },
  },
  {
    name: 'Shopping Cart',
    url: '/dashboard/e-commerce/carts', //
    iconComponent: { name: 'cil-basket' },
  },
  {
    name: 'Order',
    url: '/dashboard/e-commerce/orders', ///orders
    iconComponent: { name: 'cil-cart' },
  },
  {
    title: true,
    name: 'Reservation'
  },
  {
    name: 'Pricing and Promotion',
    url: '/dashboard/reservations/pricing-promotion', // /pricing-promotion
    iconComponent: { name: 'cil-dollar' },
  },
  {
    name: 'Reservation',
    url: '/dashboard/reservations/reservation',
    iconComponent: { name: 'cil-calendar' },
  },
  {
    title: true,
    name: 'Theme'
  },
  {
    name: 'Colors',
    url: '/dashboard/theme/colors',
    iconComponent: { name: 'cil-drop' }
  },
  {
    name: 'Typography',
    url: '/dashboard/theme/typography',
    linkProps: { fragment: 'someAnchor' },
    iconComponent: { name: 'cil-pencil' }
  },
  {
    name: 'Components',
    title: true
  },
  {
    name: 'Base',
    url: '/dashboard/base',
    iconComponent: { name: 'cil-puzzle' },
    children: [
      {
        name: 'Accordion',
        url: '/dashboard/base/accordion'
      },
      {
        name: 'Breadcrumbs',
        url: '/dashboard/base/breadcrumbs'
      },
      {
        name: 'Cards',
        url: '/dashboard/base/cards'
      },
      {
        name: 'Carousel',
        url: '/dashboard/base/carousel'
      },
      {
        name: 'Collapse',
        url: '/dashboard/base/collapse'
      },
      {
        name: 'List Group',
        url: '/dashboard/base/list-group'
      },
      {
        name: 'Navs & Tabs',
        url: '/dashboard/base/navs'
      },
      {
        name: 'Pagination',
        url: '/dashboard/base/pagination'
      },
      {
        name: 'Placeholder',
        url: '/dashboard/base/placeholder'
      },
      {
        name: 'Popovers',
        url: '/dashboard/base/popovers'
      },
      {
        name: 'Progress',
        url: '/dashboard/base/progress'
      },
      {
        name: 'Spinners',
        url: '/dashboard/base/spinners'
      },
      {
        name: 'Tables',
        url: '/dashboard/base/tables'
      },
      {
        name: 'Tabs',
        url: '/dashboard/base/tabs'
      },
      {
        name: 'Tooltips',
        url: '/dashboard/base/tooltips'
      }
    ]
  },
  {
    name: 'Buttons',
    url: '/dashboard/buttons',
    iconComponent: { name: 'cil-cursor' },
    children: [
      {
        name: 'Buttons',
        url: '/dashboard/buttons/buttons'
      },
      {
        name: 'Button groups',
        url: '/dashboard/buttons/button-groups'
      },
      {
        name: 'Dropdowns',
        url: '/dashboard/buttons/dropdowns'
      },
    ]
  },
  {
    name: 'Forms',
    url: '/forms',
    iconComponent: { name: 'cil-notes' },
    children: [
      {
        name: 'Form Control',
        url: '/dashboard/forms/form-control'
      },
      {
        name: 'Select',
        url: '/dashboard/forms/select'
      },
      {
        name: 'Checks & Radios',
        url: '/dashboard/forms/checks-radios'
      },
      {
        name: 'Range',
        url: '/dashboard/forms/range'
      },
      {
        name: 'Input Group',
        url: '/dashboard/forms/input-group'
      },
      {
        name: 'Floating Labels',
        url: '/dashboard/forms/floating-labels'
      },
      {
        name: 'Layout',
        url: '/dashboard/forms/layout'
      },
      {
        name: 'Validation',
        url: '/dashboard/forms/validation'
      }
    ]
  },
  {
    name: 'Charts',
    url: '/dashboard/charts',
    iconComponent: { name: 'cil-chart-pie' }
  },
  {
    name: 'Icons',
    iconComponent: { name: 'cil-star' },
    url: '/dashboard/icons',
    children: [
      {
        name: 'CoreUI Free',
        url: '/dashboard/icons/coreui-icons',
        badge: {
          color: 'success',
          text: 'FREE'
        }
      },
      {
        name: 'CoreUI Flags',
        url: '/dashboard/icons/flags'
      },
      {
        name: 'CoreUI Brands',
        url: '/dashboard/icons/brands'
      }
    ]
  },
  {
    name: 'Notifications',
    url: '/dashboard/notifications',
    iconComponent: { name: 'cil-bell' },
    children: [
      {
        name: 'Alerts',
        url: '/dashboard/notifications/alerts'
      },
      {
        name: 'Badges',
        url: '/dashboard/notifications/badges'
      },
      {
        name: 'Modal',
        url: '/dashboard/notifications/modal'
      },
      {
        name: 'Toast',
        url: '/dashboard/notifications/toasts'
      }
    ]
  },
  {
    name: 'Widgets',
    url: '/dashboard/widgets',
    iconComponent: { name: 'cil-calculator' },
    badge: {
      color: 'info',
      text: 'NEW'
    }
  },
  {
    title: true,
    name: 'Extras'
  },
  {
    name: 'Pages',
    url: '/login',
    iconComponent: { name: 'cil-star' },
    children: [
      {
        name: 'Login',
        url: '/login'
      },
      {
        name: 'Register',
        url: '/register'
      },
      {
        name: 'Error 404',
        url: '/404'
      },
      {
        name: 'Error 500',
        url: '/500'
      }
    ]
  },
];
