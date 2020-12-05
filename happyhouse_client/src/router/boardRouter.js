import General from '../components/Board/General';
import Notice from '../components/Board/Notice';
import Insert from '../components/Board/Insert';
import Item from '../components/Board/Item';
import Modify from '../components/Board/Modify';

const boardRoutes = [
    {
        path: '/board/general',
        name: 'general',
        component: General,
        props: true,
    },
    {
        path: '/board/notice',
        name: 'notice',
        component: Notice,
        props: true,
    },
    {
        path: '/board/insert',
        name: 'insert',
        component: Insert,
        props: true,
    },
    {
        path: '/board/item/:no',
        name: 'item',
        component: Item,
        props: true,
    },
    {
        path: '/board/modify/:no',
        name: 'modify',
        component: Modify,
        props: true,
    }
]

export default boardRoutes;