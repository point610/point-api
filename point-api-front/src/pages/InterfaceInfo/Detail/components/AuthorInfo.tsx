import { Avatar, Card } from 'antd';
import React from 'react';

interface Props {
  data: API.InterfaceInfoVO;
}

/**
 * 作者信息
 * @constructor
 */
const AuthorInfo: React.FC<Props> = (props) => {
  const { data } = props;

  const user = data?.userVO;

  if (!user) {
    return <></>;
  }

  return (
    <div style={{ marginTop: 16 }}>
      <Card.Meta
        avatar={<Avatar size={64} src={user.userAvatar} />}
        title={user.userName}
        description={user.userProfile}
      />
    </div>
  );
};

export default AuthorInfo;
