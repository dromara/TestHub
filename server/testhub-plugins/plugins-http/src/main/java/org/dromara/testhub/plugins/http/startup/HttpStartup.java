//package org.dromara.testhub.plugins.http.startup;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
//import org.dromara.testhub.nsrule.core.executer.context.RuleProject;
//import lombok.extern.slf4j.Slf4j;
//import org.dromara.testhub.plugins.http.HttpConstant;
//import org.dromara.testhub.plugins.http.server.domain.HttpCacheManager;
//import org.dromara.testhub.plugins.http.server.domain.cache.TreeCache;
//import org.dromara.testhub.plugins.http.server.repository.dao.HttpApiMapper;
//import org.dromara.testhub.plugins.http.server.repository.dao.HttpTreeNodeMapper;
//import org.dromara.testhub.plugins.http.server.repository.po.HttpApiPo;
//import org.dromara.testhub.plugins.http.server.repository.po.HttpTreeNodePo;
//import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Slf4j
//@Component
//public class HttpStartup implements ApplicationListener<ContextRefreshedEvent> {
//    @Autowired
//    private RuleConfig config;
//    @Autowired
//    private HttpTreeNodeMapper httpTreeNodeMapper;
//    @Autowired
//    private HttpApiMapper httpApiMapper;
//
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//
//        for (String key : config.getProjectMap().keySet()) {
//            RuleProject ruleProject = config.getProjectMap().get(key);
//
//            List<HttpTreeNodePo> pos = httpTreeNodeMapper.selectList(new LambdaQueryWrapper<HttpTreeNodePo>().eq(
//                                    HttpTreeNodePo::getProjectCode, ruleProject.getCode()
//                            ).orderBy(true, true, HttpTreeNodePo::getParentId)
//                            .orderBy(true, false, HttpTreeNodePo::getModifyTime)
//            );
//            TreeCache treeCache = HttpCacheManager.getTreeCache(ruleProject.getCode());
//            if (treeCache == null) {
//                treeCache = new TreeCache();
//                HttpCacheManager.put(ruleProject.getCode(), treeCache);
//            }
//            for (HttpTreeNodePo po : pos) {
//                TreeNodeResDto treeNodeResDto = new TreeNodeResDto();
//                treeNodeResDto.setKey(String.valueOf(po.getId()));
//                treeNodeResDto.setParentKey(String.valueOf(po.getParentId()));
//                treeNodeResDto.setName(po.getName());
//                treeNodeResDto.setNodeType(HttpConstant.TREE_NODE_TYPE_DIR);
//
//                treeCache.addNode(treeNodeResDto);
//            }
//
//            List<HttpApiPo> apiPos = httpApiMapper.selectList(new LambdaQueryWrapper<HttpApiPo>()
//                            .select(HttpApiPo::getTreeId,HttpApiPo::getId,HttpApiPo::getName)
//                    .eq(HttpApiPo::getProjectCode, ruleProject.getCode())
//                    .orderBy(true, false, HttpApiPo::getModifyTime));
//
//
//            for (HttpApiPo po : apiPos) {
//                TreeNodeResDto res = new TreeNodeResDto();
//                res.setKey(String.valueOf(po.getId()));
//                res.setParentKey(String.valueOf(po.getTreeId()));
//                res.setName(po.getName());
//                res.setLeaf(true);
//                res.setNodeType(HttpConstant.TREE_NODE_TYPE_API);
//
//                treeCache.addNode(res);
//            }
//
//        }
//
//    }
//
//}
