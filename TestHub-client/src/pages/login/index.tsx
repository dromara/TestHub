import React, { useEffect, useRef, useState } from 'react';
import { Button, Card, Carousel, Checkbox, Col, Form, Input, List, Row, Tag, Tooltip, Typography, message } from 'antd';
import { userLogin } from '@/service/user';
import styles from './index.less';
import Setting from '@/blocks/Setting';
import classnames from 'classnames';
import i18n from '@/i18n';
const { Paragraph, Text } = Typography;
// import { useNavigate } from 'react-router-dom';
import { getLocationHash, getThisModel, logoutClearSomeLocalStorage, navigate } from '@/utils';
import {
  getCurrentProject,
  getUserInfo,
  removeCurrentProject,
  removeUserInfo,
  setCurrentProject,
  setSatoken,
  setUserInfo,
} from '@/utils/localStorage';
import { IUserData, LoginResDto } from '@/typings/server/user';
import { RuleProjectSimpleResDto } from '@/typings/server/project';

const ipcRenderer = __UMI_ENV__ == 'desktop' ? window.require('electron').ipcRenderer : null;

const Login: React.FC = () => {
  const [projects, setProjects] = useState<RuleProjectSimpleResDto[]>([]);
  const [selectProject, setSelectProject] = useState<RuleProjectSimpleResDto | null>(null);
  const [token, setToken] = useState<string | null>(null);
  const swiperRef = useRef(null);
  const loginPlaneRef = useRef(null);
  const [passwordError, setPasswordError] = useState('');
  useEffect(() => {
    logoutClearSomeLocalStorage();
  }, []);
  const setStorage = (formData: IUserData, resDto: LoginResDto) => {
    if (formData.remember) {
      setUserInfo(formData);
    } else {
      // 否则移除
      removeUserInfo();
    }
  };
  const loginSuccess = (token: string | null) => {
    if (token == null) {
      console.error('为什么token没了');
      return;
    }
    setSatoken(token);
    if (__UMI_ENV__ == 'desktop') {
      ipcRenderer.send('login-success');
    } else {
      const params = getLocationHash();
      if (params.callback == undefined) {
        window.location.href = '/';
      } else {
        window.location.href = params.callback;
      }
    }
    navigate('/');
  };
  const projectSelectOk = () => {
    if (selectProject != null) {
      setCurrentProject(selectProject);
      loginSuccess(token);
    }
  };

  const handleLogin = async (formData: IUserData) => {
    setPasswordError('');
    const resDto = await userLogin(formData);
    // console.log('resDto' + JSON.stringify(resDto));
    if (!resDto.flag) {
      message.error(resDto.msg);
      setPasswordError(resDto.msg);
    } else {
      setToken(resDto.token);
      setSatoken(resDto.token);
      setProjects(resDto.projectDtos);
      setStorage(formData, resDto);
      //成功的时候先看当前缓存的项目
      const project = getCurrentProject();
      if (project == null) {
        //用户有项目但是缓存没有
        if (resDto.projectDtos.length == 1) {
          //  如果就一个就默认选中了
          setCurrentProject(resDto.projectDtos[0]);
          loginSuccess(resDto.token);
        } else {
          goToSlide();
          //需要选择项目
        }
      } else {
        const firstProject = resDto.projectDtos.find((project) => project.code === project.code);
        if (firstProject) {
          // 如果找到了说明他依然还有之前选择项目的权限直接跳转首页
          loginSuccess(resDto.token);
        } else {
          // 没有找到说明他需要重新选择项目
          removeCurrentProject();
          if (resDto.projectDtos.length == 1) {
            //  如果就一个就默认选中了
            setCurrentProject(resDto.projectDtos[0]);
          } else {
            goToSlide();
          }
        }
      }
    }
  };

  const goToSlide = () => {
    if (swiperRef.current) {
      swiperRef.current.next();
    }
  };

  return (
    <div className={styles.loginPage}>
      {/* <ProjectSelect
        isOpen={isProjectSelect}
        callback={() => {
          setIsProjectSelect(false);
        }}
      /> */}

      <div className={styles.loginPlane}>
        <Carousel dots={false} ref={swiperRef} className={styles.carousel}>
          <div ref={loginPlaneRef}>
            <div className={styles.login}>
              <div className={styles.loginWelcome}>{__APP_NAME__}</div>
              {/* <Tooltip
                placement="bottom"
                color={window._AppThemePack.colorBgBase}
                title={
                  <div style={{ color: window._AppThemePack.colorText, opacity: 0.8, padding: '8px 4px' }}>
                    因为我还有进步的空间
                  </div>
                }
              > */}
              <div className={styles.whyLogin}>第二懂你的自动化测试工具</div>
              {/* </Tooltip> */}

              <Form
                className={styles.loginForm}
                size="large"
                onFinish={handleLogin}
                initialValues={getUserInfo()}
                autoComplete="off"
              >
                <Form.Item<IUserData>
                  className={styles.loginFormItem}
                  name="userName"
                  rules={[{ required: true, message: i18n('login.form.user.placeholder') }]}
                >
                  <Input autoComplete="off" placeholder={i18n('login.form.user')} />
                </Form.Item>
                <Form.Item<IUserData>
                  name="password"
                  validateStatus={passwordError ? 'error' : ''}
                  rules={[
                    { required: true, message: i18n('login.form.password.placeholder') },
                    {
                      min: 6,
                      max: 12,
                      message: '密码6到10位',
                    },
                  ]}
                >
                  <Input.Password placeholder={i18n('login.form.password')} />
                </Form.Item>

                <Row justify="space-between">
                  <Col>
                    <Form.Item<IUserData> name="remember" valuePropName="checked">
                      <Checkbox>记住我</Checkbox>
                    </Form.Item>
                  </Col>
                  <Col>
                    <Form.Item>
                      <Button
                        type="link"
                        htmlType="button"
                        style={{ fontSize: 12 }}
                        onClick={() => {
                          message.warning('尚未开发');
                        }}
                      >
                        忘记密码
                      </Button>
                    </Form.Item>
                  </Col>
                </Row>
                <div className={styles.defaultPasswordTips}>
                  {getThisModel() == 'local' ? '本地模式用户名admin密码123456' : '远程模式登录信息请咨询管理员'}
                </div>
                <Button type="primary" htmlType="submit" className={styles.loginFormSubmit}>
                  {i18n('login.button.login')}
                </Button>
              </Form>
            </div>
            <div className={styles.loginSetting}>
              <Setting
                className={styles.setting}
                noLogin
                render={
                  <Button type="link" style={{ fontSize: 14 }}>
                    {i18n('setting.title.setting')}
                  </Button>
                }
              />
            </div>
          </div>
          <div>
            <div
              className={styles.projectSelect}
              style={{ height: loginPlaneRef.current ? loginPlaneRef.current.clientHeight : '100%' }}
            >
              <div className={styles.projectList}>
                <div className={styles.cardList}>
                  <List<Partial<RuleProjectSimpleResDto>>
                    rowKey="code"
                    // loading={loading}
                    grid={{
                      gutter: 16,
                      xs: 1,
                      sm: 1,
                      md: 1,
                      lg: 1,
                      xl: 1,
                      xxl: 1,
                    }}
                    dataSource={[...projects]}
                    renderItem={(item) => {
                      // if (item && item.code) {
                      return (
                        <List.Item key={item.code}>
                          <Card
                            className={classnames(styles.card, {
                              [styles.cardCurrent]: selectProject != null && selectProject.code == item.code,
                            })}
                            onClick={() => {
                              setSelectProject(item);
                            }}
                          >
                            <Card.Meta
                              avatar={<div className={styles.cardAvatar}>{item.name?.charAt(0)}</div>}
                              title={item.name}
                              description={
                                <Paragraph
                                  className={styles.item}
                                  ellipsis={{
                                    rows: 1,
                                    tooltip: item.description,
                                  }}
                                >
                                  {item.name}
                                </Paragraph>
                              }
                            />
                          </Card>
                        </List.Item>
                      );
                    }}
                  />
                </div>
              </div>
              <div className={styles.projectListBtn}>
                <Row justify="end">
                  <Col span={18}>
                    <div className={styles.projectChangeTip}>
                      {selectProject != null && (
                        <>
                          确认后你将进入
                          <Tag bordered={false} color="processing">
                            {selectProject?.name}
                          </Tag>
                        </>
                      )}
                      {selectProject == null && <>请选择要进入的项目</>}
                    </div>
                  </Col>
                  <Col>
                    <Button
                      type="primary"
                      disabled={selectProject == null}
                      style={{ fontSize: 14 }}
                      onClick={projectSelectOk}
                    >
                      确认
                    </Button>
                  </Col>
                </Row>
              </div>
            </div>
          </div>
        </Carousel>
      </div>
    </div>
  );
};

export default Login;
