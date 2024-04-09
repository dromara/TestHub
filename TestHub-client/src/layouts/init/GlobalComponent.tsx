import React from 'react';
import MyNotification from '@/components/base/MyNotification';
import Modal from '@/components/base/Modal/BaseModal';

const GlobalComponent = () => {
  return (
    <>
      <MyNotification />
      <Modal />
    </>
  );
};

export default GlobalComponent;
