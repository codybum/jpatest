import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.cresco.library.app.gPayload;
import io.cresco.library.app.pNode;
import io.cresco.library.messaging.MsgEvent;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class DBInterfaceImpl implements DBInterface {


    private Gson gson;
    private Type type;

    public BlockingQueue<String> importQueue;


    public DBInterfaceImpl() {

        this.gson = new Gson();
        this.type = new TypeToken<Map<String, List<Map<String, String>>>>() {
        }.getType();

    }

    public void shutdown() {


    }

    public Map<String,String> paramStringToMap(String param) {
        Map<String,String> params = null;

        return params;
    }

    public Map<String,String> getResourceTotal() {
        Map<String,String> resourceTotal = null;

        return resourceTotal;
    }

    public String getRegionList() {
        String queryReturn = null;


        return queryReturn;
    }

    public void submitDBImport(String exportData) {

    }

    public String getAgentList(String actionRegion) {
        String queryReturn = null;

        return queryReturn;
    }

    public String getPluginListRepo() {
        String returnString = null;

        return returnString;
    }

    public Map<String,List<pNode>> getPluginListRepoSet() {

        Map<String,List<pNode>> pluginRepoMap = null;

        return pluginRepoMap;
    }

    public List<String> getPluginListRepoInventory() {
        List<String> repoList = null;

        return repoList;
    }

    public String getPluginListByType(String actionPluginTypeId, String actionPluginTypeValue) {
        String queryReturn = null;

        return queryReturn;
    }

    public String getPluginList(String actionRegion, String actionAgent) {
        String queryReturn = null;

        return queryReturn;
    }

    public String getPluginInfo(String actionRegion, String actionAgent, String actionPlugin) {
        String queryReturn = null;

        return queryReturn;
    }

    private String getGlobalNetResourceInfo() {
        String queryReturn = null;

        return queryReturn;

    }

    private String getGlobalResourceInfo() {
        String queryReturn = null;

        return queryReturn;

    }

    private String getRegionResourceInfo(String actionRegion) {
        String queryReturn = null;

        return queryReturn;

    }

    private String getAgentResourceInfo(String actionRegion, String actionAgent) {
        String queryReturn = null;

        return queryReturn;

    }

    public String getIsAttachedMetrics(String actionRegion, String actionAgent, String actionPluginId) {
        String returnString = null;

        return returnString;
    }

    public String getNetResourceInfo() {
        String queryReturn = null;

        return queryReturn;

    }

    public String getResourceInfo(String actionRegion, String actionAgent) {
        String queryReturn = null;

        return queryReturn;

    }

    public String getGPipeline(String actionPipelineId) {
        String queryReturn = null;

        return queryReturn;

    }

    public String getGPipelineExport(String actionPipelineId) {
        String queryReturn = null;

        return queryReturn;

    }

    public String getIsAssignedInfo(String resourceid,String inodeid, boolean isResourceMetric) {

        String queryReturn = null;

        return queryReturn;

    }

    public String getPipelineInfo(String pipeline_action) {
        String queryReturn = null;

        return queryReturn;

    }

    public Map<String,String> getResourceTotal2() {
        Map<String,String> resourceTotal = null;

        return resourceTotal;
    }

    public Map<String,NodeStatusType> getEdgeHealthStatus(String region, String agent, String plugin) {

        Map<String,NodeStatusType> nodeStatusMap = null;

        return nodeStatusMap;
    }

    public Map<String,NodeStatusType> getNodeStatus(String region, String agent, String plugin) {

        Map<String,NodeStatusType> nodeStatusMap = null;

        return nodeStatusMap;
    }

    public Boolean addNode(MsgEvent de) {
        Boolean wasAdded = false;


        return wasAdded;
    }

    public Boolean watchDogUpdate(MsgEvent de) {
        Boolean wasUpdated = false;

        return wasUpdated;
    }

    public Boolean removeNode(MsgEvent de) {
        Boolean wasRemoved = false;

        return wasRemoved;
    }

    public Boolean removeNode(String region, String agent, String plugin) {
        Boolean wasRemoved = false;

        return wasRemoved;
    }

    public boolean setEdgeParam(String edgeId, String paramKey, String paramValue) {
        return false;
    }

    public Map<String,String> getEdgeParamsNoTx(String edgeId) {

        return null;
    }

    public Map<String,String> getNodeParams(String node_id) {

        return null;
    }

    public String getINodeParams(String iNode_id) {
        return null;
    }

    public String getINodeParam(String inode_id, String param) {
        return null;
    }

    public Map<String,String> getpNodeINode(String iNode_id) {
        return null;
    }

    public List<String> getANodeFromIndex(String indexName, String indexValue) {
        return null;
    }

    public boolean setINodeParam(String inode_id, String paramKey, String paramValue) {
        return false;
    }

    public String addEdge(String src_region, String src_agent, String src_plugin, String dst_region, String dst_agent, String dst_plugin, String className, Map<String,String> paramMap) {
       return null;
    }

    public String getPipeline(String pipelineId) {
        return null;
    }

    public gPayload createPipelineRecord(String tenant_id, String gPayload) {
        return null;
    }

    public String addINode(String resource_id, String inode_id) {
        return null;
    }

    public boolean updateKPI(String region, String agent, String pluginId, String resource_id, String inode_id, Map<String,String> params) {
        return false;
    }

    public String getDBExport() {
        return null;
    }

    public gPayload createPipelineNodes(gPayload gpay) {
        return null;
    }

    public boolean setPipelineStatus(String pipelineId, String status_code, String status_desc) {
        return false;
    }

    public gPayload getPipelineObj(String pipelineId) {
        return  null;
    }

    public String addINodeResource(String resource_id, String inode_id) {
        return null;
    }

    public String getNodeId(String region, String agent, String plugin) {
        return  null;
    }

    public int getPipelineStatusCode(String pipelineId) {
        return -1;
    }

    public int getINodeStatus(String INodeId) {
        return -1;
    }

    public boolean removePipeline(String pipelineId) {
        return false;
    }

    public List<String> getNodeList(String region, String agent, String plugin) {
        return null;
    }

    public String addIsAttachedEdge(String resource_id, String inode_id, String region, String agent, String plugin) {
        return null;
    }

    public String getResourceEdgeId(String resource_id, String inode_id) {
        return null;
    }
    public String getIsAssignedParam(String edge_id,String param_name) {
        return null;
    }

    public boolean setDBImport(String exportData) {
        return false;
    }

}
