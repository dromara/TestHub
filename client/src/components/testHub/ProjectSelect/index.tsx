import React, { useEffect, useState } from 'react';
import styles from './index.less';
import classnames from 'classnames';
import { Badge, Button, Card, Col, List, Modal, Row, Tag, Typography } from 'antd';
import { RuleProjectSimpleResDto } from '@/typings/server/project';
import { getProjectSimpleList } from '@/service/project';
import { getCurrentProject, setCurrentProject } from '@/utils/localStorage';
const { Paragraph, Text } = Typography;

interface IProps {
  className?: string;
  isOpen: boolean;
  callback: Function;
}

function ProjectSelect(props: IProps) {
  const [isLoad, setIsLoad] = useState<boolean>(false);
  const [loading, setLoading] = useState<boolean>(true);
  const project = getCurrentProject();
  const [selectProject, setSelectProject] = useState<RuleProjectSimpleResDto | null>(project);
  const [projects, setProjects] = useState<RuleProjectSimpleResDto[]>([]);

  function isChange() {
    if (project != null && selectProject != null) {
      if (project.code != selectProject.code) {
        //有变更
        return true;
      } else {
        //两个相等 没有变更
        return false;
      }
    } else if (project == null && selectProject != null) {
      //原来没有现在有了 说明有更新
      return true;
    }
  }

  useEffect(() => {
    if (!isLoad) {
      const getProjects = getProjectSimpleList();
      getProjects.then((result) => {
        setProjects(result);
        setLoading(false);
        setIsLoad(true);
      });
      getProjects.catch((error) => {
        setLoading(false);
        setIsLoad(true);
      });
    }
  }, []);

  return (
    <Modal
      open={props.isOpen}
      title="切换项目"
      destroyOnClose
      onOk={() => {
        if (isChange()) {
          setCurrentProject(selectProject);
          props.callback(true);
        } else {
          props.callback(false);
        }
        // setIsOpen(false);
        return true;
      }}
      onCancel={() => {
        props.callback(false);
        // setIsOpen(false);
      }}
      width={800}
      maskClosable={false}
      footer={(_, { OkBtn, CancelBtn }) => (
        <>
          <Row>
            <Col span={16}>
              <div className={styles.projectChangeTip}>
                {isChange() && (
                  <>
                    确认后你将切换至
                    <Tag bordered={false} color="processing">
                      {selectProject?.name}
                    </Tag>
                    项目，请注意保存尚未保存的窗口
                  </>
                )}
              </div>
            </Col>
            <Col span={8}>
              <CancelBtn />
              <OkBtn />
            </Col>
          </Row>
        </>
      )}
    >
      <div className={styles.projectList}>
        <div className={styles.cardList}>
          <List<Partial<RuleProjectSimpleResDto>>
            rowKey="code"
            loading={loading}
            grid={{
              gutter: 16,
              xs: 1,
              sm: 2,
              md: 2,
              lg: 3,
              xl: 3,
              xxl: 3,
            }}
            dataSource={[...projects]}
            renderItem={(item) => {
              // if (item && item.code) {
              return (
                <List.Item key={item.code}>
                  {project != null && project.code == item.code && (
                    <>
                      <Badge.Ribbon text="当前">
                        <Card
                          className={classnames(styles.card, {
                            [styles.cardCurrent]: selectProject != null && selectProject.code == item.code,
                          })}
                          onClick={() => {
                            setSelectProject(item);
                          }}
                        >
                          {/* <Card hoverable className={styles.card} actions={[<a key="option2">进入</a>]}> */}
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
                                {/* {item.description} */}
                              </Paragraph>
                            }
                          />
                        </Card>
                      </Badge.Ribbon>
                    </>
                  )}
                  {project == null ||
                    (project.code != item.code && (
                      <>
                        <Card
                          className={classnames(styles.card, {
                            [styles.cardCurrent]: selectProject != null && selectProject.code == item.code,
                          })}
                          onClick={() => {
                            setSelectProject(item);
                          }}
                        >
                          {/* <Card hoverable className={styles.card} actions={[<a key="option2">进入</a>]}> */}
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
                                {/* {item.description} */}
                              </Paragraph>
                            }
                          />
                        </Card>
                      </>
                    ))}
                </List.Item>
              );
              // }
              // return (
              //   <List.Item>
              //     <Button type="dashed" className={styles.newButton}>
              //       <PlusOutlined /> 新增项目
              //     </Button>
              //   </List.Item>
              // );
            }}
          />
        </div>
      </div>
    </Modal>
  );
}

export default ProjectSelect;
