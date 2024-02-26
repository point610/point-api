import {DownloadOutlined} from '@ant-design/icons';
import PictureUploader from '@/components/PictureUploader';
import {useModel, useParams} from '@@/exports';
import {PageContainer} from '@ant-design/pro-components';
import {Button, Card, Col, message, Row, Input, Typography} from 'antd';
import moment from 'moment';
import React, {useEffect, useState} from 'react';
import {getUserByIdUsingGet, updateMyUserAkskUsingPost, updateMyUserUsingPost} from "@/services/backend/userController";

const {Paragraph} = Typography;

const {TextArea} = Input;
/**
 * 生成器详情页
 * @constructor
 */
const GeneratorDetailPage: React.FC = () => {

  const [loading, setLoading] = useState<boolean>(false);
  const [data, setData] = useState<API.User>({});
  const {initialState} = useModel('@@initialState');
  const {currentUser} = initialState ?? {};
  const id = currentUser?.id;
  const [basicInfo, setBasicInfo] = useState<API.User>();

  /**
   * 加载数据
   */
  const loadData = async () => {
    console.log(currentUser)
    if (!id) {
      return;
    }
    setLoading(true);
    try {
      const res = await getUserByIdUsingGet({
        id,
      });
      setData(res.data || {});
    } catch (error: any) {
      message.error('获取数据失败，' + error.message);
    }
    setLoading(false);
  };

  useEffect(() => {
    loadData();
  }, [id]);

  /**
   * 更新
   * @param values
   */
  const doUpdate = async (values: API.UserUpdateMyRequest) => {
    try {
      const res = await updateMyUserUsingPost(values);
      if (res.data) {
        setData(res.data || {});
        message.success('更新成功');
      }
    } catch (error: any) {
      message.error('更新失败，' + error.message);
    }
  };
  /**
   * 更新aksk
   * @param values
   */
  const updateAKSK = async () => {
    try {
      const res = await updateMyUserAkskUsingPost();
      console.log(res)
      if (res.data) {
        setData(res.data || {});
        message.success('更新成功');
      }
    } catch (error: any) {
      message.error('更新失败，' + error.message);
    }
  };

  /**
   * 提交
   * @param values
   */
  const doSubmit = async () => {
    await doUpdate({
      ...data,
    });
  };


  return (
    <PageContainer title={<></>} loading={loading}>
      {/*个人信息设置*/}
      <Card title="个人信息设置" bordered={false} headStyle={{background: "#eae5f1"}}
            extra={<Button onClick={doSubmit}>保存修改</Button>}>
        <Row justify="space-between" gutter={[32, 32]}>
          <Col flex="auto">
            <Row>
              <Col span={2}><Button type="text">名称</Button></Col>
              <Col span={22}>
                <Input defaultValue={data.userName} onChange={(e) => {
                  data.userName = e.target.value;
                }}
                />
              </Col>
            </Row>
            <p></p>
            <Row>
              <Col span={2}><Button type="text">简介</Button></Col>
              <Col span={22}>
                <TextArea defaultValue={data.userProfile}
                          onChange={(e) => {
                            data.userProfile = e.target.value;
                          }}
                />
              </Col>
            </Row>
            <p></p>
            <Row>
              <Col span={2}><Button type="text">头像</Button></Col>
              <Col span={22}>
                <PictureUploader biz="user_avatar" value={data.userAvatar}
                                 onChange={(value) => {
                                   data.userAvatar = value;
                                 }}
                />
              </Col>
            </Row>
            <p></p>
            <Row>
              <Col span={2}><Button type="text">时间</Button></Col>
              <Col span={22}><Button type="text">{moment(data.createTime).format('YYYY-MM-DD HH:mm:ss')}</Button></Col>
            </Row>
            <p></p>
          </Col>
        </Row>
      </Card>
      <p></p>
      {/*开发者凭证*/}
      <Card title="开发者凭证" bordered={false} headStyle={{background: "#eae5f1"}}
            extra={<Button onClick={updateAKSK}>更新凭证</Button>}>
        <Row justify="space-between" gutter={[32, 32]}>
          <Col flex="auto">
            <Row>
              <Col span={4}><Paragraph>AccessKey</Paragraph></Col>
              <Col span={20}>
                <Paragraph copyable>{data.accessKey}</Paragraph>
              </Col>
            </Row>
            <p></p>
            <Row>
              <Col span={4}><Paragraph>SecretKey</Paragraph></Col>
              <Col span={20}>
                <Paragraph copyable>{data.secretKey}</Paragraph>
              </Col>
            </Row>
            <p></p>
          </Col>
        </Row>
      </Card>
      <p></p>
      {/* 开发者 SDK */}
      <Card title="开发者 SDK" bordered={false} headStyle={{background: "#eae5f1"}}>
        <Row justify="space-between" gutter={[32, 32]}>
          <Col flex="auto">
            <Button size={"large"} target="_blank" href={"https://github.com/point610/point-api-sdk"}
                    icon={<DownloadOutlined/>}>
              Java SDK
            </Button>
          </Col>
        </Row>
      </Card>
    </PageContainer>
  );
};

export default GeneratorDetailPage;
