import io.cresco.library.app.gPayload;
import io.cresco.library.app.pNode;
import io.cresco.library.messaging.MsgEvent;

import java.util.List;
import java.util.Map;

public interface DBInterface {



    //init
    public void shutdown();

    //core
    public Boolean addNode(MsgEvent de);
    public Boolean watchDogUpdate(MsgEvent de);
    public Boolean removeNode(MsgEvent de);
    public Boolean removeNode(String region, String agent, String plugin);
    public boolean updateKPI(String region, String agent, String pluginId, String resource_id, String inode_id, Map<String, String> params);
    public String getDBExport();
    public gPayload createPipelineRecord(String tenant_id, String gPayload);
    public String addINode(String resource_id, String inode_id);
    public gPayload createPipelineNodes(gPayload gpay);
    public boolean setPipelineStatus(String pipelineId, String status_code, String status_desc);
    public gPayload getPipelineObj(String pipelineId);
    public String addINodeResource(String resource_id, String inode_id);
    public String getNodeId(String region, String agent, String plugin);
    public int getPipelineStatusCode(String pipelineId);
    public int getINodeStatus(String INodeId);
    public boolean removePipeline(String pipelineId);

    //helper
    public Map<String,String> paramStringToMap(String param);
    public boolean setDBImport(String exportData);
    public void submitDBImport(String exportData);

    //dashboard
    public Map<String,String> getResourceTotal();
    public String getRegionList();
    public String getAgentList(String actionRegion);
    public String getPluginListRepo();
    public Map<String, List<pNode>> getPluginListRepoSet();
    public List<String> getPluginListRepoInventory();
    public String getPluginListByType(String actionPluginTypeId, String actionPluginTypeValue);
    public String getPluginList(String actionRegion, String actionAgent);
    public String getPluginInfo(String actionRegion, String actionAgent, String actionPlugin);
    public String getIsAttachedMetrics(String actionRegion, String actionAgent, String actionPluginId);
    public String getNetResourceInfo();
    public String getResourceInfo(String actionRegion, String actionAgent);
    public String getGPipeline(String actionPipelineId);
    public String getGPipelineExport(String actionPipelineId);
    public String getIsAssignedInfo(String resourceid, String inodeid, boolean isResourceMetric);
    public String getPipelineInfo(String pipeline_action);
    public Map<String, NodeStatusType> getEdgeHealthStatus(String region, String agent, String plugin);
    public Map<String, NodeStatusType> getNodeStatus(String region, String agent, String plugin);
    public boolean setEdgeParam(String edgeId, String paramKey, String paramValue);
    public Map<String,String> getEdgeParamsNoTx(String edgeId);
    public Map<String,String> getNodeParams(String node_id);
    public String getINodeParams(String iNode_id);
    public String getINodeParam(String inode_id, String param);
    public Map<String,String> getpNodeINode(String iNode_id);
    public List<String> getANodeFromIndex(String indexName, String indexValue);
    public boolean setINodeParam(String inode_id, String paramKey, String paramValue);
    public String addEdge(String src_region, String src_agent, String src_plugin, String dst_region, String dst_agent, String dst_plugin, String className, Map<String, String> paramMap);
    public String getPipeline(String pipelineId);
    public List<String> getNodeList(String region, String agent, String plugin);
    public String addIsAttachedEdge(String resource_id, String inode_id, String region, String agent, String plugin);
    public String getResourceEdgeId(String resource_id, String inode_id);
    public String getIsAssignedParam(String edge_id, String param_name);


}