import General from '../components/Board/General';
import Notice from '../components/Board/Notice';
import Insert from '../components/Board/Insert';
import Item from '../components/Board/Item';
import Modify from '../components/Board/Modify';

const boardRoutes = [
    {//자유게시판
        path: '/board/general',
        name: 'general',
        component: General,
        props: true,
    },
    {//공지사항
        path: '/board/notice',
        name: 'notice',
        component: Notice,
        props: true,
    },
    {//글작성
        path: '/board/insert',
        name: 'insert',
        component: Insert,
        props: true,
    },
    {//게시글
        path: '/board/item/:no',
        name: 'item',
        component: Item,
        props: true,
    },
    {//글 수정
        path: '/board/modify/:no',
        name: 'modify',
        component: Modify,
        props: true,
    }
]

export default boardRoutes;