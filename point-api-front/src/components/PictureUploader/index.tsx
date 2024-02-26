import {LoadingOutlined, PlusOutlined} from '@ant-design/icons';
import {message, Upload, UploadProps} from 'antd';
import React, {useState} from 'react';
import {uploadImgUsingPost} from "@/services/backend/imgController";

interface Props {
  biz: string;
  onChange?: (url: string) => void;
  value?: string;
}

/**
 * 图片上传组件
 * @constructor
 */
const PictureUploader: React.FC<Props> = (props) => {
  const {biz, value, onChange} = props;
  const [loading, setLoading] = useState(false);
  const [userAvatar, setUserAvatar] = useState(String);

  const uploadProps: UploadProps = {
    name: 'file',
    listType: 'picture-card',
    multiple: false,
    maxCount: 1,
    showUploadList: false,
    customRequest: async (fileObj: any) => {
      setLoading(true);
      try {
        const res = await uploadImgUsingPost(
          {
            biz,
          },
          {},
          fileObj.file,
        );
        // 拼接完整图片路径
        // const fullPath = COS_HOST + res.data;
        const fullPath = res.data;
        onChange?.(fullPath ?? '');
        fileObj.onSuccess(fullPath);
        setUserAvatar(res.data);
      } catch (e: any) {
        message.error('上传失败，' + e.message);
        fileObj.onError(e);
      }
      setLoading(false);
    },
  };

  const uploadButton = (
    <div>
      {loading ? <LoadingOutlined/> : <PlusOutlined/>}
      <div style={{marginTop: 8}}>上传</div>
    </div>
  );

  return (
    <Upload {...uploadProps}>
      {userAvatar ? <img src={userAvatar} alt="picture" style={{width: '100%'}}/> :
        <img src={value} alt="picture" style={{width: '100%'}}/>}
    </Upload>
  );
};

export default PictureUploader;
