import { monaco } from 'react-monaco-editor';
import { dom_suggestions } from './item/suggestions/dom';

const searchStrEach = (str: string, target: string) => {
  let sum = 0;
  for (const key of str) {
    if (key == target) {
      sum++;
    }
  }
  return sum;
};

const initCaseXml = () => {
  monaco.languages.register({ id: 'casexml' });
  //右键格式化
  // monaco.languages.registerDocumentFormattingEditProvider('casexml', {
  //   async provideDocumentFormattingEdits(model, options, token) {
  //     return [
  //       {
  //         range: model.getFullModelRange(),
  //         text: xmlFormat(model.getValue()),
  //       },
  //     ];
  //   },
  // });
  // 定义自定义主题
  monaco.editor.defineTheme('BlackTheme', {
    base: 'vs-dark',
    inherit: true,
    rules: [],
    colors: {
      // 相关颜色属性配置
      'editor.foreground': '#ffffff',
      'editor.background': '#15161a', //背景色
    },
  });
  // monaco.languages.registerCompletionItemProvider('casexml', {
  //   provideCompletionItems: (model, position) => {
  //     return {
  //       suggestions: [
  //         {
  //           label: 'example1',
  //           kind: monaco.languages.CompletionItemKind.Text,
  //           insertText: 'example1',
  //         },
  //         {
  //           label: 'example2',
  //           kind: monaco.languages.CompletionItemKind.Keyword,
  //           insertText: 'example2',
  //         },
  //       ],
  //     };
  //   },
  // });
  //代码提示
  monaco.languages.registerCompletionItemProvider('casexml', {
    provideCompletionItems: (model, position) => {
      const codePre = model.getValueInRange({
        startLineNumber: position.lineNumber,
        startColumn: 1,
        endLineNumber: position.lineNumber,
        endColumn: position.column,
      });
      const codePreTrim = codePre.trim();
      // console.log(codePreTrim);
      let suggestions = dom_suggestions;

      if (codePreTrim.length > 0) {
        const attr = codePreTrim.substring(
          codePreTrim.lastIndexOf(' ') + 1,
          codePreTrim.lastIndexOf('='),
        );
        const tag = codePreTrim.substring(1, codePreTrim.indexOf(' '));
        if (tag != '<') {
          if (tag == 'rule') {
            //最后一个等于号之后“是否出现 是两次 说明在输入标签的属性项而不是属性值
            if (searchStrEach(codePreTrim.substring(codePreTrim.lastIndexOf('=') + 1), '"') == 1) {
              return {
                suggestions: [{ label: '', insertText: '', detail: '' }],
              };
            }
            return {
              suggestions: [
                {
                  label: 'code',
                  insertText: ['code=""'].join('\n'),
                  detail: '编码',
                },
                {
                  label: 'name',
                  insertText: ['name=""'].join('\n'),
                  detail: '名称',
                },

                {
                  label: 'model',
                  insertText: ['model="flow"'].join('\n'),
                  detail: '模式',
                },
              ],
            };
          } else if (tag == 'param') {
            //最后一个等于号之后“是否出现 是两次 说明在输入标签的属性项而不是属性值
            if (searchStrEach(codePreTrim.substring(codePreTrim.lastIndexOf('=') + 1), '"') == 1) {
              if (attr == 'dataType') {
                return {
                  suggestions: [
                    {
                      label: 'NUMBER',
                      insertText: ['NUMBER'].join('\n'),
                      detail: '数值',
                    },
                    {
                      label: 'STRING',
                      insertText: ['STRING'].join('\n'),
                      detail: '字符串',
                    },
                    {
                      label: 'BOLL',
                      insertText: ['BOLL'].join('\n'),
                      detail: '布尔',
                    },
                    {
                      label: 'MAP',
                      insertText: ['MAP'].join('\n'),
                      detail: '键值对',
                    },
                    {
                      label: 'TIME_YMD',
                      insertText: ['TIME_YMD'].join('\n'),
                      detail: '年月日',
                    },
                    {
                      label: 'TIME_HMS',
                      insertText: ['TIME_HMS'].join('\n'),
                      detail: '时分秒',
                    },
                    {
                      label: 'TIME_YMDHMS',
                      insertText: ['TIME_YMDHMS'].join('\n'),
                      detail: '年月日时分秒',
                    },
                  ],
                };
              }
            }
            return {
              suggestions: [
                {
                  label: 'code',
                  insertText: ['code=""'].join('\n'),
                  detail: '编码',
                },
                {
                  label: 'name',
                  insertText: ['name=""'].join('\n'),
                  detail: '名称',
                },
                {
                  label: 'dataType',
                  insertText: ['dataType=""'].join('\n'),
                  detail: '数据类型',
                },
                {
                  label: 'data',
                  insertText: ['data=""'].join('\n'),
                  detail: '默认值',
                },
                {
                  label: 'complex',
                  insertText: ['complex="1"'].join('\n'),
                  detail: '维度',
                },
                {
                  label: 'necessary',
                  insertText: ['necessary="true"'].join('\n'),
                  detail: '是否必填',
                },
              ],
            };
          } else if (tag == 'action') {
            if (searchStrEach(codePreTrim.substring(codePreTrim.lastIndexOf('=') + 1), '"') == 1) {
              if (attr == 'type') {
                return {
                  suggestions: [

                    {
                      label: 'CONST',
                      insertText: ['CONST'].join('\n'),
                      detail: '常量',
                    },
                    {
                      label: 'CONVERT',
                      insertText: ['CONVERT'].join('\n'),
                      detail: '数据处理',
                    },
                    {
                      label: 'MAP',
                      insertText: ['MAP'].join('\n'),
                      detail: '键值对',
                    },
                    {
                      label: 'JS',
                      insertText: ['JS'].join('\n'),
                      detail: 'Js脚本',
                    },
                    {
                      label: 'SQL',
                      insertText: ['SQL'].join('\n'),
                      detail: 'SQL语句',
                    },
                    {
                      label: 'SQL_CALL',
                      insertText: ['SQL_CALL'].join('\n'),
                      detail: 'SQL存储过程',
                    },
                  ],
                };
              }
              if (attr == 'dataType') {
                return {
                  suggestions: [
                    {
                      label: 'NUMBER',
                      insertText: ['NUMBER'].join('\n'),
                      detail: '数值',
                    },
                    {
                      label: 'STRING',
                      insertText: ['STRING'].join('\n'),
                      detail: '字符串',
                    },
                    {
                      label: 'BOLL',
                      insertText: ['BOLL'].join('\n'),
                      detail: '布尔',
                    },
                    {
                      label: 'MAP',
                      insertText: ['MAP'].join('\n'),
                      detail: '键值对',
                    },
                    {
                      label: 'TIME_YMD',
                      insertText: ['TIME_YMD'].join('\n'),
                      detail: '年月日',
                    },
                    {
                      label: 'TIME_HMS',
                      insertText: ['TIME_HMS'].join('\n'),
                      detail: '时分秒',
                    },
                    {
                      label: 'TIME_YMDHMS',
                      insertText: ['TIME_YMDHMS'].join('\n'),
                      detail: '年月日时分秒',
                    },
                  ],
                };
              }
            }
            return {
              suggestions: [
                {
                  label: 'code',
                  insertText: ['code=""'].join('\n'),
                  detail: '编码',
                },
                {
                  label: 'name',
                  insertText: ['name=""'].join('\n'),
                  detail: '名称',
                },
                {
                  label: 'dataType',
                  insertText: ['dataType=""'].join('\n'),
                  detail: '返回值类型',
                },
                {
                  label: 'complex',
                  insertText: ['complex="1"'].join('\n'),
                  detail: '返回值维度',
                },
              ],
            };
          } else if (tag == 'flow') {
            if (searchStrEach(codePreTrim.substring(codePreTrim.lastIndexOf('=') + 1), '"') == 1) {
              // if (attr == 'code') {
              //   return {
              //     suggestions: [{ label: 'flow', insertText: 'flow', detail: '流程ID' }],
              //   };
              // } else {
              return {
                suggestions: [{ label: '', insertText: '', detail: '' }],
              };
              // }
            }
            return {
              suggestions: [
                {
                  label: 'code',
                  insertText: ['code=""'].join('\n'),
                  detail: '编码',
                },
                {
                  label: 'name',
                  insertText: ['name=""'].join('\n'),
                  detail: '名称',
                },
              ],
            };
          } else if (tag == 'execute') {
            if (searchStrEach(codePreTrim.substring(codePreTrim.lastIndexOf('=') + 1), '"') != 2) {
              // if (attr == 'code') {
              //   return {
              //     suggestions: [{ label: 'flow', insertText: 'flow', detail: '流程ID' }],
              //   };
              // } else {
              return {
                suggestions: [{ label: '', insertText: '', detail: '' }],
              };
              // }
            } else {
              return {
                suggestions: [
                  {
                    label: 'code',
                    insertText: ['code=""'].join('\n'),
                    detail: '编码',
                  },
                  {
                    label: 'name',
                    insertText: ['name=""'].join('\n'),
                    detail: '名称',
                  },
                  {
                    label: 'actionCode',
                    insertText: ['actionCode=""'].join('\n'),
                    detail: '行为编码',
                  },
                  {
                    label: 'block',
                    insertText: ['block="true"'].join('\n'),
                    detail: '是否异常阻断',
                  },
                  {
                    label: 'conKey',
                    insertText: ['conKey=""'].join('\n'),
                    detail: '事务ID',
                  },
                  {
                    label: 'sleepTime',
                    insertText: ['sleepTime="1000"'].join('\n'),
                    detail: '停顿时间',
                  },
                  {
                    label: 'init',
                    insertText: ['init="false"'].join('\n'),
                    detail: '是否接受返回值',
                  },
                ],
              };
            }
          } else if (tag == 'convert') {
            if (searchStrEach(codePreTrim.substring(codePreTrim.lastIndexOf('=') + 1), '"') == 1) {
              if (attr == 'type') {
                return {
                  suggestions: [
                    {
                      label: 'DEL',
                      insertText: ['DEL'].join('\n'),
                      detail: '删除',
                    },
                    {
                      label: 'PUT',
                      insertText: ['PUT'].join('\n'),
                      detail: '设置',
                    },
                  ],
                };
              }
            }
            return {
              suggestions: [
                {
                  label: 'code',
                  insertText: ['code=""'].join('\n'),
                  detail: '属性',
                },
                {
                  label: 'name',
                  insertText: ['name=""'].join('\n'),
                  detail: '名称',
                },
                {
                  label: 'type',
                  insertText: ['type=""'].join('\n'),
                  detail: '类型',
                },
                {
                  label: 'data',
                  insertText: ['data=""'].join('\n'),
                  detail: '数据',
                },
              ],
            };
          } else if (tag == 'command') {
            if (searchStrEach(codePreTrim.substring(codePreTrim.lastIndexOf('=') + 1), '"') == 1) {
              if (attr == 'op') {
                return {
                  suggestions: [
                    {
                      label: 'del',
                      insertText: ['del'].join('\n'),
                      detail: '该命令用于在 key 存在时删除 key',
                    },
                    {
                      label: 'get',
                      insertText: ['get'].join('\n'),
                      detail: '获取指定 key 的值',
                    },
                    {
                      label: 'hget',
                      insertText: ['hget'].join('\n'),
                      detail: '获取存储在哈希表中指定字段的值',
                    },
                    {
                      label: 'hgetall',
                      insertText: ['hgetall'].join('\n'),
                      detail: '获取在哈希表中指定 key 的所有字段和值',
                    },
                    {
                      label: 'llen',
                      insertText: ['llen'].join('\n'),
                      detail: '获取列表长度',
                    },
                    {
                      label: 'lindex',
                      insertText: ['lindex'].join('\n'),
                      detail: '通过索引获取列表中的元素',
                    },
                    {
                      label: 'strlen',
                      insertText: ['strlen'].join('\n'),
                      detail: '返回 key 所储存的字符串值的长度',
                    },
                    {
                      label: 'exists',
                      insertText: ['exists'].join('\n'),
                      detail: '检查给定 key 是否存在',
                    },
                    {
                      label: 'hmset',
                      insertText: ['hmset'].join('\n'),
                      detail: '同时将多个 field-value (域-值)对设置到哈希表 key 中',
                    },
                    {
                      label: 'set',
                      insertText: ['set'].join('\n'),
                      detail: '设置指定 key 的值',
                    },
                    {
                      label: 'rpush',
                      insertText: ['rpush'].join('\n'),
                      detail: '在列表中添加一个或多个值到列表尾部',
                    },

                    {
                      label: 'mset',
                      insertText: ['mset'].join('\n'),
                      detail: '同时设置一个或多个 key-value 对',
                    },
                  ],
                };
              }
            }
            return {
              suggestions: [
                {
                  label: 'code',
                  insertText: ['code=""'].join('\n'),
                  detail: '编码',
                },
                {
                  label: 'op',
                  insertText: ['op=""'].join('\n'),
                  detail: '命令名称',
                },
                {
                  label: 'key',
                  insertText: ['key=""'].join('\n'),
                  detail: '键',
                },
                {
                  label: 'index',
                  insertText: ['index="0"'].join('\n'),
                  detail: '索引',
                },
                {
                  label: 'convert',
                  insertText: ['convert=""'].join('\n'),
                  detail: '后置转换',
                },
                {
                  label: 'field',
                  insertText: ['field=""'].join('\n'),
                  detail: 'hash中的key',
                },
                {
                  label: 'data',
                  insertText: ['data=""'].join('\n'),
                  detail: '数据',
                },
              ],
            };
          } else if (tag == 'body') {
            if (searchStrEach(codePreTrim.substring(codePreTrim.lastIndexOf('=') + 1), '"') == 1) {
              if (attr == 'type') {
                return {
                  suggestions: [
                    {
                      label: 'raw',
                      insertText: ['raw'].join('\n'),
                      detail: 'raw',
                    },
                    {
                      label: 'x-www-form-urlencoded',
                      insertText: ['x-www-form-urlencoded'].join('\n'),
                      detail: 'x-www-form-urlencoded',
                    }, {
                      label: 'none',
                      insertText: ['none'].join('\n'),
                      detail: 'none',
                    },
                  ],
                };
              } else if (attr == 'language') {
                return {
                  suggestions: [
                    {
                      label: 'xml',
                      insertText: ['xml'].join('\n'),
                      detail: 'xml',
                    },
                    {
                      label: 'json',
                      insertText: ['json'].join('\n'),
                      detail: 'json',
                    }, {
                      label: 'text',
                      insertText: ['text'].join('\n'),
                      detail: 'text',
                    },
                  ],
                };
              }
            }
            return {
              suggestions: [
                {
                  label: 'language',
                  insertText: ['language="json"'].join('\n'),
                  detail: '语言',
                },
                {
                  label: 'type',
                  insertText: ['type="json"'].join('\n'),
                  detail: '类型',
                },
              ],
            };
          } else if (tag == 'httpModel') {
            if (searchStrEach(codePreTrim.substring(codePreTrim.lastIndexOf('=') + 1), '"') == 1) {
              if (attr == 'method') {
                return {
                  suggestions: [
                    {
                      label: 'POST',
                      insertText: ['POST'].join('\n'),
                      detail: 'POST',
                    }, {
                      label: 'GET',
                      insertText: ['GET'].join('\n'),
                      detail: 'GET',
                    }, {
                      label: 'DELETE',
                      insertText: ['DELETE'].join('\n'),
                      detail: 'DELETE',
                    }, {
                      label: 'PUT',
                      insertText: ['PUT'].join('\n'),
                      detail: 'PUT',
                    },
                  ],
                };
              } else if (attr == 'language') {
                return {
                  suggestions: [
                    {
                      label: 'xml',
                      insertText: ['xml'].join('\n'),
                      detail: 'xml',
                    },
                    {
                      label: 'json',
                      insertText: ['json'].join('\n'),
                      detail: 'json',
                    }, {
                      label: 'text',
                      insertText: ['text'].join('\n'),
                      detail: 'text',
                    },
                  ],
                };
              }
            }
            return {
              suggestions: [
                {
                  label: 'code',
                  insertText: ['code=""'].join('\n'),
                  detail: '属性',
                },
                {
                  label: 'name',
                  insertText: ['name=""'].join('\n'),
                  detail: '名称',
                },
                {
                  label: 'type',
                  insertText: ['type=""'].join('\n'),
                  detail: '类型',
                },
                {
                  label: 'data',
                  insertText: ['data=""'].join('\n'),
                  detail: '数据',
                },
              ],
            };
          }
          if (
            tag == 'bound' ||
            tag == 'script' ||
            tag == 'flows' ||
            tag == 'params' ||
            tag == 'converts'
          ) {
            return {
              suggestions: [{ label: '', insertText: '', detail: '' }],
            };
          }
        } else {
          suggestions = dom_suggestions;
        }
      } else {
        suggestions = dom_suggestions;
      }
      return {
        suggestions: JSON.parse(JSON.stringify(suggestions)),
      };
    },
  });
  //鼠标说明
  monaco.languages.registerHoverProvider('casexml', {
    provideHover: function (document, position, token) {
      const codePre = document.getValueInRange({
        startLineNumber: position.lineNumber,
        startColumn: 1,
        endLineNumber: position.lineNumber,
        endColumn: position.column,
      });
      const codePreTrim = codePre.trim();
      // console.log('codePreTrim' + codePreTrim);
      const tag = codePreTrim.substring(1, codePreTrim.indexOf(' '));

      if (document.getWordAtPosition(position) != null) {
        const word = document.getWordAtPosition(position).word;
        // console.log('word=' + word);
        if (tag.trim().length > 1 && tag.trim() != '') {
          //标签 + 属性
          if (tag == 'rule') {
            if (word == 'code') {
              return {
                contents: [
                  { value: '**编码**:' },
                  {
                    value: ['必填且唯一'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'name') {
              return {
                contents: [
                  { value: '**名称**:' },
                  {
                    value: ['建议唯一'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'model') {
              return {
                contents: [
                  { value: '**模式**:目前仅支持folw' },
                  { value: '**可选值**' },
                  {
                    value: ['flow'].join('\n\n'),
                  },
                ],
              };
            }
          }
          else if (tag == 'flow') {
            if (word == 'code') {
              return {
                contents: [
                  { value: '**编码**:' },
                  {
                    value: ['必填且唯一'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'name') {
              return {
                contents: [
                  { value: '**名称**:' },
                  {
                    value: ['建议唯一'].join('\n\n'),
                  },
                ],
              };
            }
          }
          else if (tag == 'param') {
            if (word == 'code') {
              return {
                contents: [
                  { value: '**编码**:' },
                  {
                    value: ['必填且唯一'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'data') {
              return {
                contents: [
                  { value: '**默认值**:' },
                  {
                    value: ['可输入任意formula表达式'].join('\n\n'),
                  },
                ],
              };
            }

            if (word == 'necessary') {
              return {
                contents: [
                  { value: '**是否必填**' },
                  {
                    value: ['如果必填被调用时没有接收到会报错'].join('\n\n'),
                  },
                  { value: '**可选值**' },
                  {
                    value: ['默认false,true'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'complex') {
              return {
                contents: [
                  { value: '**维度**:' },
                  {
                    value: ['代表数据的维度'].join('\n\n'),
                  },
                  { value: '**可选值**' },
                  { value: '默认为0,可选任意数字' },
                ],
              };
            }
            if (word == 'name') {
              return {
                contents: [
                  { value: '**名称**:' },
                  {
                    value: ['建议唯一'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'dataType') {
              return {
                contents: [
                  { value: '**数据类型**' },
                  {
                    value: ['必填'].join('\n\n'),
                  },
                  { value: '**可选值**' },
                  {
                    value: [
                      'NUMBER数值',
                      'STRING字符串',
                      'BOLL布尔',
                      'MAP键值对',
                      'TIME_YMD年月日',
                      'TIME_HMS时分秒',
                      'TIME_YMDHMS年月日时分秒',
                    ].join('\n\n'),
                  },
                ],
              };
            }
          }
          else if (tag == 'action') {
            if (word == 'code') {
              return {
                contents: [
                  { value: '**编码**:' },
                  {
                    value: ['必填且唯一'].join('\n\n'),
                  },
                ],
              };
            }

            if (word == 'complex') {
              return {
                contents: [
                  { value: '**维度**:' },
                  {
                    value: ['代表返回值的维度'].join('\n\n'),
                  },
                  { value: '**可选值**' },
                  { value: '默认为0,可选任意数字' },
                ],
              };
            }
            if (word == 'name') {
              return {
                contents: [
                  { value: '**名称**:' },
                  {
                    value: ['建议唯一'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'dataType') {
              return {
                contents: [
                  { value: '**数据类型**' },
                  {
                    value: ['必填，返回值的数据类型'].join('\n\n'),
                  },
                  { value: '**可选值**' },
                  {
                    value: [
                      'NUMBER数值',
                      'STRING字符串',
                      'BOLL布尔',
                      'MAP键值对',
                      'TIME_YMD年月日',
                      'TIME_HMS时分秒',
                      'TIME_YMDHMS年月日时分秒',
                    ].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'type') {
              return {
                contents: [
                  { value: '**能力类型**' },
                  {
                    value: ['必填，返回值的数据类型'].join('\n\n'),
                  },
                  { value: '**可选值**' },
                  {
                    value: [
                      'SQL-执行sql',
                      'SQL_CALL-存储过程',
                      'HTTP-HTTP请求',
                      'JS-运行JS脚本',
                      'CONST-定义常量',
                      'CONVERT-数据处理',
                    ].join('\n\n'),
                  },
                ],
              };
            }
          }
          else if (tag == 'execute') {
            if (word == 'name') {
              return {
                contents: [
                  { value: '**名称**:' },
                  {
                    value: ['建议唯一'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'code') {
              return {
                contents: [
                  { value: '**编码**:' },
                  {
                    value: ['必填且唯一'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'actionCode') {
              return {
                contents: [
                  { value: '**行为编码**:' },
                  {
                    value: ['可选你定义的行为code和全局行为编码'].join('\n\n'),
                  },
                ],
              };
            }

            if (word == 'conKey') {
              return {
                contents: [
                  { value: '**事务ID**' },
                  {
                    value: ['conKey事务ID(SQL,SQL_BEGIN,SQL_COMMIT可选)'].join('\n\n'),
                  },
                  { value: '**可选值**' },
                  {
                    value: ['默认与步骤的code相同'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'sleepTime') {
              return {
                contents: [
                  { value: '**停顿时间**' },
                  {
                    value: ['停顿时间(SLEEP必须选),单位为秒'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'init') {
              return {
                contents: [{ value: '**是否接受返回值**:' }, { value: 'true默认接收,false不接收' }],
              };
            }
            if (word == 'block') {
              return {
                contents: [{ value: '**是否异常阻断**:' }, { value: 'true阻断,false默认不阻断' }],
              };
            }
            if (word == 'dataType') {
              return {
                contents: [
                  { value: '**数据类型**' },
                  {
                    value: ['必填'].join('\n\n'),
                  },
                  { value: '**可选值**' },
                  {
                    value: [
                      'NUMBER数值',
                      'STRING字符串',
                      'BOLL布尔',
                      'MAP键值对',
                      'TIME_YMD年月日',
                      'TIME_HMS时分秒',
                      'TIME_YMDHMS年月日时分秒',
                    ].join('\n\n'),
                  },
                ],
              };
            }
          }
          else if (tag == 'inject') {
            if (word == 'code') {
              return {
                contents: [
                  { value: '**参数**:' },
                  {
                    value: ['必填且唯一'].join('\n\n'),
                  },
                  { value: '**可选值**:' },
                  {
                    value: ['可选值范围为被调用的行为声明的参数'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'data') {
              return {
                contents: [
                  { value: '**数据值**' },
                  {
                    value: ['可输入任意formula表达式'].join('\n\n'),
                  },
                ],
              };
            }
          }
          else if (tag == 'checkItem') {
            if (word == 'code') {
              return {
                contents: [
                  { value: '**编码**:' },
                  {
                    value: ['必填且唯一'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'name') {
              return {
                contents: [
                  { value: '**名称**:' },
                  {
                    value: ['建议唯一'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'msg') {
              return {
                contents: [{ value: '**错误提示**' }],
              };
            }
          }
          else if (tag == 'checkObj') {
            if (word == 'code') {
              return {
                contents: [
                  { value: '**编码**:' },
                  {
                    value: ['必填且唯一'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'name') {
              return {
                contents: [
                  { value: '**名称**:' },
                  {
                    value: ['建议唯一'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'msg') {
              return {
                contents: [{ value: '**错误提示**' }],
              };
            }
            if (word == 'cover') {
              return {
                contents: [
                  { value: '**被比较对象**' },
                  {
                    value: ['可输入任意formula表达式'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'threshold') {
              return {
                contents: [
                  { value: '**比较对象**' },
                  {
                    value: ['可输入任意formula表达式'].join('\n\n'),
                  },
                ],
              };
            }
          }
          else if (tag == 'expression') {
            if (word == 'operationCode') {
              return {
                contents: [
                  { value: '**操作符**:' },
                  {
                    value: ['逻辑或关系运算支持的操作符不同'].join('\n\n'),
                  },
                  { value: '**logic逻辑运算**' },
                  {
                    value: ['or或', 'and且'].join('\n\n'),
                  },
                  { value: '**elation关系运算**' },
                  {
                    value: ['eq等于\tneq不等于', 'gt大于\tle小于等于', 'lt小于\tge大于等于'].join(
                      '\n\n',
                    ),
                  },
                ],
              };
            }
            if (word == 'expressionType') {
              return {
                contents: [
                  { value: '**类型**:' },
                  { value: '**可选值**' },
                  {
                    value: ['logic逻辑运算;relation关系运算'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'dataType') {
              return {
                contents: [
                  { value: '**数据类型**' },
                  {
                    value: ['必填'].join('\n\n'),
                  },
                  { value: '**可选值**' },
                  {
                    value: [
                      'NUMBER数值',
                      'STRING字符串',
                      'BOLL布尔',
                      'MAP键值对',
                      'TIME_YMD年月日',
                      'TIME_HMS时分秒',
                      'TIME_YMDHMS年月日时分秒',
                    ].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'cover') {
              return {
                contents: [
                  { value: '**被阈值**' },
                  {
                    value: ['可输入任意formula表达式'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'threshold') {
              return {
                contents: [
                  { value: '**阈值**' },
                  {
                    value: ['可输入任意formula表达式'].join('\n\n'),
                  },
                ],
              };
            }
          }
          else if (tag == 'convert') {
            if (word == 'code') {
              return {
                contents: [{ value: '**code参数**:' }],
              };
            }
            if (word == 'name') {
              return {
                contents: [{ value: '**名称**:' }],
              };
            }
            if (word == 'type') {
              return {
                contents: [
                  { value: '**类型**:' },
                  { value: '**可选值**' },
                  {
                    value: ['DEL删除;PUT设置'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'data') {
              return {
                contents: [
                  { value: '**数据**' },
                  { value: '**可选值**' },
                  {
                    value: ['可输入任意formula表达式'].join('\n\n'),
                  },
                ],
              };
            }
          }
          else if (tag == 'command') {
            if (word == 'code') {
              return {
                contents: [{ value: '**code参数**' }],
              };
            }
            if (word == 'op') {
              return {
                contents: [
                  { value: '**命令名称**' },
                  { value: '**可选值**' },
                  {
                    value: [
                      'del:删除key',
                      'exists:检查给定key是否存在',
                      'get:获取key的值',
                      'hget:获取哈希表中指定字段的值',
                      'hgetall:获取哈希表完整的kv',
                      'llen:获取列表长度',
                      'lindex:通过索引获取列表中的元素',
                      'strlen:返回key所储存的字符串值的长度',
                      'mset:同时设置一个或多个key-value对',
                      'rpush:在列表中添加一个或多个值到列表尾部',
                      'set:设置指定key的值',
                      'hmset:同时将多个(域-值)对设置到哈希表',
                    ].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'key') {
              return {
                contents: [{ value: '**被操作的键**' }],
              };
            }
            if (word == 'index') {
              return {
                contents: [
                  { value: '**索引**:' },
                  { value: '**可选值**' },
                  {
                    value: ['任意formula表达式'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'field') {
              return {
                contents: [
                  { value: '**field**:' },
                  {
                    value: ['hash数据中的key'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'convert') {
              return {
                contents: [
                  { value: '**后置转换**' },
                  { value: '**可选值**' },
                  {
                    value: ['任意formula表达式'].join('\n\n'),
                  },
                ],
              };
            }
            if (word == 'data') {
              return {
                contents: [
                  { value: '**数据**' },
                  { value: '**可选值**' },
                  {
                    value: ['可输入任意formula表达式'].join('\n\n'),
                  },
                ],
              };
            }
          } else if (tag == 'httpModel') {
            if (word == 'url') {
              return {
                contents: [{ value: '**请求路径**' }],
              };
            } else if (word == 'method') {
              return {
                contents: [
                  { value: '**请求方式**' },
                  { value: '**可选值**' },
                  {
                    value: [
                      'POST',
                      'GET',
                      'PUT',
                      'DELETE',
                    ].join('\n\n'),
                  }
                ],
              };
            }
          } else if (tag == 'body') {
            if (word == 'type') {
              return {
                contents: [
                  { value: '**请求体类型**' },
                  { value: '**可选值**' },
                  {
                    value: [
                      'none',
                      'form-data',
                      'x-www-form-urlencoded',
                      'raw',
                    ].join('\n\n'),
                  }],
              };
            } else if (word == 'language') {
              return {
                contents: [
                  { value: '**请求数据类型**' },
                  { value: '仅仅在type为raw时生效' },
                  { value: '**可选值**' },
                  {
                    value: [
                      'json',
                      'xml',
                      'text',
                    ].join('\n\n'),
                  }],
              };
            }
          }
          return {
            // contents: [{ value: '**标签 + 属性**' }],
          };
        } else {
          //标签
          if (word == 'rule') {
            return {
              contents: [
                { value: '**用例**:每一个测试用例的根标签都是rule' },

                { value: '**必选参数**' },
                {
                  value: ['code编码;name名称;model模式[flow]'].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: ['params参数组;actions行为组;folws流程组'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'params') {
            return {
              contents: [
                { value: '**参数组**' },
                {
                  value: ['用于定义用例或行为所需接收的参数'].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: ['param参数申明'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'param') {
            return {
              contents: [
                { value: '**参数申明**' },
                {
                  value: ['用于声明参数'].join('\n\n'),
                },
                { value: '**必选参数**' },
                {
                  value: ['code编码;dataType数据类型'].join('\n\n'),
                },
                { value: '**可选参数**' },
                {
                  value: ['name名称;data默认值;complex维度[0];necessary是否必填[false],true'].join(
                    '\n\n',
                  ),
                },
              ],
            };
          } else if (word == 'actions') {
            return {
              contents: [
                { value: '**行为组**' },
                {
                  value: ['用于定义用例可以执行的动作'].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: ['action行为定义'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'action') {
            return {
              contents: [
                { value: '**行为定义**:用于定义行为的细节' },
                { value: '**必选参数**' },
                {
                  value: ['code编码;type能力类型;dataType返回值类型'].join('\n\n'),
                },
                { value: '**可选参数**' },
                {
                  value: ['name名称;complex返回值维度[0]'].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: [
                    'params参数申明;script脚本(JS必选);\n\nbound动态字符串(SQL,SQL_CALL,HTTP,CONST必选)',
                  ].join('\n\n'),
                },
              ],
            };
          } else if (word == 'flows') {
            return {
              contents: [
                { value: '**流程组**' },
                {
                  value: ['用于定义用例可以执行的流程列表'].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: ['flow流程'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'flow') {
            return {
              contents: [
                { value: '**流程**' },
                {
                  value: ['用于定义执行的流程'].join('\n\n'),
                },
                { value: '**必选参数**' },
                {
                  value: ['code编码'].join('\n\n'),
                },
                { value: '**可选参数**' },
                {
                  value: ['name名称'].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: ['execute步骤'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'execute') {
            return {
              contents: [
                { value: '**步骤**:用于定义流程中的步骤' },
                { value: '**必选参数**' },
                {
                  value: ['code编码;name名称;actionCode行为编码'].join('\n\n'),
                },
                { value: '**可选参数**' },
                {
                  value: [
                    'block异常阻断[false],true',
                    'init接受返回值[false],true',
                    'sleepTime停顿时间(SLEEP必须选)',
                    'conKey事务ID(SQL,SQL_BEGIN,SQL_COMMIT可选)',
                  ].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: [
                    'injects参数传递组(可选)',
                    'checkItem检查项(CHECK必选)',
                    'checkObj对象比较(CHECK_OBJ必选)',
                  ].join('\n\n'),
                },
              ],
            };
          } else if (word == 'injects') {
            return {
              contents: [
                { value: '**参数传递组**:用于向被调用的行为传递参数' },
                { value: '**子组件**' },
                {
                  value: ['inject参数传递'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'inject') {
            return {
              contents: [
                { value: '**参数传递**:定义传递规则' },
                { value: '**必选参数**' },
                {
                  value: ['code参数;data数据值'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'checkItem') {
            return {
              contents: [
                { value: '**检查项**:定义检查项' },
                { value: '**必选参数**' },
                {
                  value: ['code参数;name检查名称;msg异常提示'].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: ['expression运算表达式'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'expression') {
            return {
              contents: [
                { value: '**运算表达式**:逻辑运算、算数运算' },
                { value: '**必选参数**' },
                {
                  value: ['expressionType类型relation,logic;operationCode运算符'].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: ['subExpressions子表达式(logic必选)'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'subExpressions') {
            return {
              contents: [
                { value: '**子表达式**:' },
                { value: '**子组件**' },
                {
                  value: ['expression运算表达式'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'actions') {
            return {
              contents: [
                { value: '**行为组**' },
                {
                  value: ['用于定义用例可以执行的动作'].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: ['action行为定义'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'checkObj') {
            return {
              contents: [
                { value: '**对象比较**:定义对象比较' },
                { value: '**必选参数**' },
                {
                  value: [
                    'code参数;name检查名称;msg异常提示',
                    'cover被比较对象;threshold比较对象',
                  ].join('\n\n'),
                },
              ],
            };
          } else if (word == 'bound') {
            return {
              contents: [{ value: '**bound**:动态字符串' }],
            };
          } else if (word == 'script') {
            return {
              contents: [{ value: '**script**:脚本' }],
            };
          } else if (word == 'converts') {
            return {
              contents: [
                { value: '**转换组**:用于定义转换规则转换的列表' },
                { value: '**子组件**' },
                {
                  value: ['convert转换规则'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'convert') {
            return {
              contents: [
                { value: '**转换**:用于定义转换规则' },
                { value: '**必选参数**' },
                {
                  value: ['code参数;name名称'].join('\n\n'),
                },
                { value: '**可选参数**' },
                {
                  value: ['data数据(PUT必选)'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'commands') {
            return {
              contents: [
                { value: '**命令组**' },
                {
                  value: ['用于定义操作redis的命令'].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: ['command命令'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'command') {
            return {
              contents: [
                { value: '**redis的命令**' },
                { value: '**必选参数**' },
                {
                  value: ['op命令'].join('\n\n'),
                },
                { value: '**可选参数**' },
                {
                  value: [
                    'code编码(mset必填)',
                    'key操作键(非mset必填)',
                    'data数据(设置类操作必填)',
                    'index数据(含索引操作必填)',
                    'field数据中的key',
                    'convert后置操作',
                  ].join('\n\n'),
                },
              ],
            };
          } else if (word == 'httpModel') {
            return {
              contents: [
                { value: '**Http请求设置**' },
                { value: '**必选参数**' },
                {
                  value: [
                    'url请求地址',
                    'method请求方式',
                  ].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: ['headers请求头',
                    'rests路径参数',
                    'params参数',
                    'body请求体'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'rests') {
            return {
              contents: [
                { value: '**rests路径参数**' },
                {
                  value: ['用于配置rest路径参数'].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: ['param参数'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'params') {
            return {
              contents: [
                { value: '**params参数**' },
                {
                  value: ['用于配置拼接到url中的参数'].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: ['param参数'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'headers') {
            return {
              contents: [
                { value: '**headers参数**' },
                {
                  value: ['用于配置请求头的参数'].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: ['param参数'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'body') {
            return {
              contents: [
                { value: '**请求体设置**' },
                { value: '**必选参数**' },
                {
                  value: [
                    'type类型',
                  ].join('\n\n'),
                },
                { value: '**可选参数**' },
                {
                  value: [
                    'language语言',
                  ].join('\n\n'),
                },
                { value: '**子组件**' },
                {
                  value: ['content文本内容',
                    'param参数'].join('\n\n'),
                },
              ],
            };
          } else if (word == 'content') {
            return {
              contents: [
                { value: '**文本内容**' },
                {
                  value: ['支持配置freemarker脚本'].join('\n\n'),
                },
              ],
            };
          }
        }
      }
    },
  });

  monaco.languages.setLanguageConfiguration('casexml', {
    comments: {
      blockComment: ['<!--', '-->'],
    },
    brackets: [
      ['{', '}'],
      ['[', ']'],
    ],
    autoClosingPairs: [
      { open: '{', close: '}' },
      { open: '[', close: ']' },
      { open: '<', close: '>', notIn: ['string'] },
      { open: '"', close: '"' },
    ],
    surroundingPairs: [
      { open: '(', close: ')' },
      { open: '[', close: ']' },
      { open: '`', close: '`' },
    ],
    folding: {
      markers: {
        start: new RegExp('^\\\\s*<!--\\\\s*#?region\\\\b.*-->'),
        end: new RegExp('^\\\\s*<!--\\\\s*#?endregion\\\\b.*-->'),
      },
    },
  });
  //关键字
  monaco.languages.setMonarchTokensProvider('casexml', {
    ignoreCase: true,

    // The main tokenizer for our languages
    tokenizer: {
      root: [
        [/<\?xml/, 'metatag', '@doctype'],
        [/<!--/, 'comment', '@comment'],
        [/(<)((?:[\w\-]+:)?[\w\-]+)(\s*)(\/>)/, ['delimiter', 'tag', '', 'delimiter']],
        // [/(<)(script)/, ['delimiter', { token: 'tag', next: '@script' }]],
        [/(<)((?:[\w\-]+:)?[\w\-]+)/, ['delimiter', { token: 'tag', next: '@otherTag' }]],
        [/(<\/)((?:[\w\-]+:)?[\w\-]+)/, ['delimiter', { token: 'tag', next: '@otherTag' }]],
        [/</, 'delimiter'],
        [/[^<]+/], // text
      ],

      doctype: [
        [/[^>]+/, 'metatag.content'],
        [/>/, 'metatag', '@pop'],
      ],

      comment: [
        [/-->/, 'comment', '@pop'],
        [/[^-]+/, 'comment.content'],
        [/./, 'comment.content'],
      ],

      otherTag: [
        [/\/?>/, 'delimiter', '@pop'],
        [/"([^"]*)"/, 'attribute.value'],
        [/'([^']*)'/, 'attribute.value'],
        [/[\w\-]+/, 'attribute.name'],
        [/=/, 'delimiter'],
        [/[ \t\r\n]+/], // whitespace
      ],
    },
  });
};

export default initCaseXml;
