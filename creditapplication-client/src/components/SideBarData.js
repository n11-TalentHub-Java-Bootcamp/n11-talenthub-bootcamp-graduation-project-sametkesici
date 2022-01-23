import React from 'react';
import UpdateIcon from '../icons/update.svg'
import SearchIcon from '../icons/search.svg'
import SaveIcon from '../icons/add.svg'

export const SideBarData = [
  {
    title: 'Kullanıcı Kaydet',
    path: '/',
    icon: <img width={'50px'} height={'50px'} src={SaveIcon}/>,
    cName: 'nav-text'
  },
  {
    title: 'Kullanıcı Güncelle',
    path: '/update-customer',
    icon: <img width={'50px'} height={'50px'} src={UpdateIcon}/>,
    cName: 'nav-text mt-3'
  },
  {
    title: 'Başvuru Bul',
    path: '/find-application',
    icon: <img width={'50px'} height={'50px'} src={SearchIcon}/>,
    cName: 'nav-text mt-2'
  },
];