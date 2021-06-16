package com.didichuxing.datachannel.agentmanager.thirdpart.agent.manage.extension.impl;

import com.alibaba.fastjson.JSON;
import com.didichuxing.datachannel.agentmanager.common.bean.domain.agent.AgentDO;
import com.didichuxing.datachannel.agentmanager.common.bean.domain.agent.operationtask.AgentOperationTaskDO;
import com.didichuxing.datachannel.agentmanager.common.bean.po.agent.AgentPO;
import com.didichuxing.datachannel.agentmanager.common.enumeration.ErrorCodeEnum;
import com.didichuxing.datachannel.agentmanager.common.exception.ServiceException;
import com.didichuxing.datachannel.agentmanager.common.bean.common.CheckResult;
import com.didichuxing.datachannel.agentmanager.common.util.ConvertUtil;
import com.didichuxing.datachannel.agentmanager.common.util.HttpUtils;
import com.didichuxing.datachannel.agentmanager.thirdpart.agent.manage.extension.AgentManageServiceExtension;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@org.springframework.stereotype.Service
public class DefaultAgentManageServiceExtensionImpl implements AgentManageServiceExtension {

    @Override
    public AgentPO agent2AgentPO(AgentDO agent) throws ServiceException {
        if(null == agent) {
            throw new ServiceException(
                    String.format(
                            "class=AgentManageServiceExtensionImpl||method=agent2AgentPO||msg={%s}",
                            "入参agent对象不可为空"
                    ),
                    ErrorCodeEnum.ILLEGAL_PARAMS.getCode()
            );
        }
        AgentPO agentPO = null;
        try {
            agentPO = ConvertUtil.obj2Obj(agent, AgentPO.class);
        } catch (Exception ex) {
            throw new ServiceException(
                    String.format(
                            "class=AgentManageServiceExtensionImpl||method=agent2AgentPO||msg={%s}",
                            String.format("Agent对象={%s}转化为AgentPO对象失败，原因为：%s", JSON.toJSONString(agent), ex.getMessage())
                    ),
                    ex,
                    ErrorCodeEnum.SYSTEM_INTERNAL_ERROR.getCode()
            );
        }
        if(null == agentPO) {
            throw new ServiceException(
                    String.format(
                            "class=AgentManageServiceExtensionImpl||method=agent2AgentPO||msg={%s}",
                            String.format("Agent对象={%s}转化为AgentPO对象失败", JSON.toJSONString(agent))
                    ),
                    ErrorCodeEnum.SYSTEM_INTERNAL_ERROR.getCode()
            );
        }
        return agentPO;
    }

    @Override
    public CheckResult checkCreateParameterAgent(AgentDO agent) {

        //TODO：

        //TODO：校验待更新 agent 版本是否在系统维护的 agentVersion 列表存在，如不存在，表示非法

        return new CheckResult(true);

    }

    @Override
    public CheckResult checkDeleteParameterAgent(AgentDO agent) {

        //TODO：

        return new CheckResult(true);

    }

    @Override
    public AgentOperationTaskDO agent2AgentOperationTaskUnInstall(AgentDO agent) throws ServiceException {

        //TODO：

        return null;

    }

    @Override
    public AgentOperationTaskDO agent2AgentOperationTaskInstall(AgentDO agent) throws ServiceException {

        //TODO：

        return null;

    }

    @Override
    public AgentDO agentPO2AgentDO(AgentPO agentPO) throws ServiceException {
        if(null == agentPO) {
            throw new ServiceException(
                    String.format(
                            "class=AgentManageServiceExtensionImpl||method=agentPO2AgentDO||msg={%s}",
                            "入参agentPO对象不可为空"
                    ),
                    ErrorCodeEnum.ILLEGAL_PARAMS.getCode()
            );
        }
        AgentDO agent = null;
        try {
            agent = ConvertUtil.obj2Obj(agentPO, AgentDO.class);
        } catch (Exception ex) {
            throw new ServiceException(
                    String.format(
                            "class=AgentManageServiceExtensionImpl||method=agentPO2AgentDO||msg={%s}",
                            String.format("AgentPO对象={%s}转化为Agent对象失败，原因为：%s", JSON.toJSONString(agentPO), ex.getMessage())
                    ),
                    ex,
                    ErrorCodeEnum.SYSTEM_INTERNAL_ERROR.getCode()
            );
        }
        if(null == agent) {
            throw new ServiceException(
                    String.format(
                            "class=AgentManageServiceExtensionImpl||method=agentPO2AgentDO||msg={%s}",
                            String.format("AgentPO对象={%s}转化为Agent对象失败", JSON.toJSONString(agentPO))
                    ),
                    ErrorCodeEnum.SYSTEM_INTERNAL_ERROR.getCode()
            );
        }
        return agent;
    }

    @Override
    public CheckResult checkUpdateParameterAgent(AgentDO agentDO) {
        if(null == agentDO) {
            return new CheckResult(
                    false,
                    ErrorCodeEnum.ILLEGAL_PARAMS.getCode(),
                    "入参agentDO对象不可为空"
            );
        }
        if(null == agentDO.getId()) {
            return new CheckResult(
                    false,
                    ErrorCodeEnum.ILLEGAL_PARAMS.getCode(),
                    "入参agentDO对象id属性值不可为空"
            );
        }

        //TODO：校验待更新 agent 版本是否在系统维护的 agentVersion 列表存在，如不存在，表示非法

        return new CheckResult(true);
    }

    @Override
    public AgentDO updateAgent(AgentDO sourceAgent, AgentDO targetAgent) throws ServiceException {
        sourceAgent.setCpuLimitThreshold(targetAgent.getCpuLimitThreshold());
        sourceAgent.setByteLimitThreshold(targetAgent.getByteLimitThreshold());
        sourceAgent.setMetricsSendReceiverId(targetAgent.getMetricsSendReceiverId());
        sourceAgent.setMetricsSendTopic(targetAgent.getMetricsSendTopic());
        sourceAgent.setErrorLogsSendReceiverId(targetAgent.getErrorLogsSendReceiverId());
        sourceAgent.setErrorLogsSendTopic(targetAgent.getErrorLogsSendTopic());
        sourceAgent.setAdvancedConfigurationJsonString(targetAgent.getAdvancedConfigurationJsonString());
        return sourceAgent;
    }

    @Override
    public List<AgentDO> agentPOList2AgentDOList(List<AgentPO> agentPOList) {
        return ConvertUtil.list2List(agentPOList, AgentDO.class);
    }

}