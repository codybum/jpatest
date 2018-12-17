import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.cresco.agent.controller.core.ControllerEngine;
import io.cresco.library.app.gPayload;
import io.cresco.library.app.pNode;
import io.cresco.library.messaging.MsgEvent;
import io.cresco.library.plugin.PluginBuilder;
import io.cresco.library.utilities.CLogger;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DBInterfaceImplForController implements DBInterface {

    private PluginBuilder plugin;
    private CLogger logger;
    private ControllerEngine controllerEngine;
    private DBEngine dbe;

    private Gson gson;
    private Type type;

    public BlockingQueue<String> importQueue;


    public DBInterfaceImplForController(ControllerEngine controllerEngine) {
        this.controllerEngine = controllerEngine;
        this.plugin = controllerEngine.getPluginBuilder();
        this.logger = plugin.getLogger(DBInterfaceImpl.class.getName(),CLogger.Level.Info);
        this.dbe = new DBEngine(this.controllerEngine);

        this.importQueue = new LinkedBlockingQueue<>();
        this.gson = new Gson();
        this.type = new TypeToken<Map<String, List<Map<String, String>>>>() {
        }.getType();

    }

    public Boolean addNode(MsgEvent de) {
        Boolean wasAdded = false;

        try {

            logger.info("Adding Node: " + de.getParams().toString());

            String region = de.getParam("region_name");
            String agent = de.getParam("agent_name");
            String plugin = de.getParam("plugin_id");

            if(region != null) {
                if(!dbe.nodeExist(region,null,null)) {
                    //fixme take into account current state
                    //add region, this will need to be more complex in future
                    dbe.addNode(region,null,null,"0","Region added by Agent",null);
                }
                if(agent != null) {
                    if(!dbe.nodeExist(region,agent,null)) {
                        //fixme take into account current state
                        //add region, this will need to be more complex in future

                        de.setParam("is_active",Boolean.TRUE.toString());
                        de.setParam("watchdog_ts", String.valueOf(System.currentTimeMillis()));

                        String platform = de.getParam("platform");
                        String environment = de.getParam("environment");
                        String location = de.getParam("location");
                        String watchdogtimer = de.getParam("watchdogtimer");
                        Map<String,String> configparams = new HashMap<>();
                        configparams.put("platform",platform);
                        configparams.put("environment",environment);
                        configparams.put("location",location);
                        configparams.put("watchdogtimer",watchdogtimer);

                        dbe.addNode(region,agent,null,"0","Agent added by Agent",gson.toJson(configparams));
                    }
                }

            }



            //Is Agent
            if((region != null) && (agent != null) && (plugin == null)) {
                if (de.getParam("pluginconfigs") != null) {
                    List<Map<String, String>> configMapList = new Gson().fromJson(de.getCompressedParam("pluginconfigs"),
                            new TypeToken<List<Map<String, String>>>() {
                            }.getType());

                    for (Map<String, String> configMap : configMapList) {
                        String pluginId = configMap.get("pluginid");
                        //gdb.addNode(region, agent, pluginId);
                        //gdb.setNodeParams(region, agent, pluginId, configMap);
                        //{status_code=10, status_dest=Plugin Active, pluginid=plugin/0, isactive=true, configparams={"pluginname":"io.cresco.repo","jarfile":"repo-1.0-SNAPSHOT.jar"}}
                        logger.info("Adding Sub-Node: " + configMap.toString());

                    }
                }
            }

            wasAdded = true;

        } catch (Exception ex) {
            logger.error("GraphDBUpdater : addNode ERROR : " + ex.toString());
        }
        return wasAdded;
    }


    public Map<String,NodeStatusType> getEdgeHealthStatus(String region, String agent, String plugin) {

        Map<String,NodeStatusType> nodeStatusMap = null;
        logger.error("Map<String,NodeStatusType> getEdgeHealthStatus(String region, String agent, String plugin)");
        return nodeStatusMap;
    }

    public Boolean watchDogUpdate(MsgEvent de) {
        Boolean wasUpdated = false;
        logger.error("Boolean watchDogUpdate(MsgEvent de)");
        return wasUpdated;
    }

    //complete

    public void shutdown() {
        logger.error("shutdown()");
    }

    public Map<String,String> paramStringToMap(String param) {
        Map<String,String> params = null;
        logger.error("Map<String,String> paramStringToMap(String param)");
        return params;
    }

    public Map<String,String> getResourceTotal() {
        Map<String,String> resourceTotal = null;
        logger.error("Map<String,String> getResourceTotal()");
        return resourceTotal;
    }

    public String getRegionList() {
        String queryReturn = null;
        logger.error("String getRegionList()");
        return queryReturn;
    }

    public void submitDBImport(String exportData) {
        logger.error("void submitDBImport(String exportData)");
    }

    public String getAgentList(String actionRegion) {
        String queryReturn = null;
        logger.error("String getAgentList(String actionRegion)");
        return queryReturn;
    }

    public String getPluginListRepo() {
        String returnString = null;
        logger.error("String getPluginListRepo()");
        return returnString;
    }

    public Map<String,List<pNode>> getPluginListRepoSet() {

        Map<String,List<pNode>> pluginRepoMap = null;
        logger.error("Map<String,List<pNode>> getPluginListRepoSet()");
        return pluginRepoMap;
    }

    public List<String> getPluginListRepoInventory() {
        List<String> repoList = null;
        logger.error("List<String> getPluginListRepoInventory()");
        return repoList;
    }

    public String getPluginListByType(String actionPluginTypeId, String actionPluginTypeValue) {
        String queryReturn = null;
        logger.error("String getPluginListByType(String actionPluginTypeId, String actionPluginTypeValue)");
        return queryReturn;
    }

    public String getPluginList(String actionRegion, String actionAgent) {
        String queryReturn = null;
        logger.error("String getPluginList(String actionRegion, String actionAgent)");
        return queryReturn;
    }

    public String getPluginInfo(String actionRegion, String actionAgent, String actionPlugin) {
        String queryReturn = null;
        logger.error("String getPluginInfo(String actionRegion, String actionAgent, String actionPlugin)");
        return queryReturn;
    }

    private String getGlobalNetResourceInfo() {
        String queryReturn = null;
        logger.error("String getGlobalNetResourceInfo()");
        return queryReturn;

    }

    private String getGlobalResourceInfo() {
        String queryReturn = null;
        logger.error("String getGlobalResourceInfo()");
        return queryReturn;

    }

    private String getRegionResourceInfo(String actionRegion) {
        String queryReturn = null;
        logger.error("String getRegionResourceInfo(String actionRegion)");
        return queryReturn;

    }

    private String getAgentResourceInfo(String actionRegion, String actionAgent) {
        String queryReturn = null;
        logger.error("String getAgentResourceInfo(String actionRegion, String actionAgent)");
        return queryReturn;

    }

    public String getIsAttachedMetrics(String actionRegion, String actionAgent, String actionPluginId) {
        String returnString = null;
        logger.error("String getIsAttachedMetrics(String actionRegion, String actionAgent, String actionPluginId)");
        return returnString;
    }

    public String getNetResourceInfo() {
        String queryReturn = null;
        logger.error("String getNetResourceInfo()");
        return queryReturn;

    }

    public String getResourceInfo(String actionRegion, String actionAgent) {
        String queryReturn = null;
        logger.error("String getResourceInfo(String actionRegion, String actionAgent)");
        return queryReturn;

    }

    public String getGPipeline(String actionPipelineId) {
        String queryReturn = null;
        logger.error("String getGPipeline(String actionPipelineId)");
        return queryReturn;

    }

    public String getGPipelineExport(String actionPipelineId) {
        String queryReturn = null;
        logger.error("String getGPipelineExport(String actionPipelineId)");
        return queryReturn;

    }

    public String getIsAssignedInfo(String resourceid,String inodeid, boolean isResourceMetric) {

        String queryReturn = null;
        logger.error("String getIsAssignedInfo(String resourceid,String inodeid, boolean isResourceMetric");
        return queryReturn;

    }

    public String getPipelineInfo(String pipeline_action) {
        String queryReturn = null;
        logger.error("String getPipelineInfo(String pipeline_action)");
        return queryReturn;

    }

    public Map<String,String> getResourceTotal2() {
        Map<String,String> resourceTotal = null;
        logger.error("Map<String,String> getResourceTotal2()");
        return resourceTotal;
    }

    public Map<String,NodeStatusType> getNodeStatus(String region, String agent, String plugin) {

        Map<String,NodeStatusType> nodeStatusMap = null;
        logger.error("Map<String,NodeStatusType> getNodeStatus(String region, String agent, String plugin)");
        return nodeStatusMap;
    }

    public Boolean removeNode(MsgEvent de) {
        Boolean wasRemoved = false;
        logger.error("Boolean removeNode(MsgEvent de)");
        return wasRemoved;
    }

    public Boolean removeNode(String region, String agent, String plugin) {
        Boolean wasRemoved = false;
        logger.error("Boolean removeNode(String region, String agent, String plugin)");
        return wasRemoved;
    }

    public boolean setEdgeParam(String edgeId, String paramKey, String paramValue) {
        logger.error("boolean setEdgeParam(String edgeId, String paramKey, String paramValue)");
        return false;
    }

    public Map<String,String> getEdgeParamsNoTx(String edgeId) {
        logger.error("Map<String,String> getEdgeParamsNoTx(String edgeId)");
        return null;
    }

    public Map<String,String> getNodeParams(String node_id) {
        logger.error("Map<String,String> getNodeParams(String node_id)");
        return null;
    }

    public String getINodeParams(String iNode_id) {
        logger.error("String getINodeParams(String iNode_id)");
        return null;
    }

    public String getINodeParam(String inode_id, String param) {
        logger.error("String getINodeParam(String inode_id, String param)");
        return null;
    }

    public Map<String,String> getpNodeINode(String iNode_id) {
        logger.error("Map<String,String> getpNodeINode(String iNode_id)");
        return null;
    }

    public List<String> getANodeFromIndex(String indexName, String indexValue) {
        logger.error("List<String> getANodeFromIndex(String indexName, String indexValue)");
        return null;
    }

    public boolean setINodeParam(String inode_id, String paramKey, String paramValue) {
        logger.error("setINodeParam(String inode_id, String paramKey, String paramValue)");
        return false;
    }

    public String addEdge(String src_region, String src_agent, String src_plugin, String dst_region, String dst_agent, String dst_plugin, String className, Map<String,String> paramMap) {
        logger.error("String addEdge(String src_region, String src_agent, String src_plugin, String dst_region, String dst_agent, String dst_plugin, String className, Map<String,String> paramMap)");
        return null;
    }

    public String getPipeline(String pipelineId) {
        logger.error("String getPipeline(String pipelineId)");
        return null;
    }

    public gPayload createPipelineRecord(String tenant_id, String gPayload) {
        logger.error("gPayload createPipelineRecord(String tenant_id, String gPayload)");
        return null;
    }

    public String addINode(String resource_id, String inode_id) {
        logger.error("String addINode(String resource_id, String inode_id)");
        return null;
    }

    public boolean updateKPI(String region, String agent, String pluginId, String resource_id, String inode_id, Map<String,String> params) {
        logger.error("boolean updateKPI(String region, String agent, String pluginId, String resource_id, String inode_id, Map<String,String> params)");
        return false;
    }

    public String getDBExport() {
        logger.error("String getDBExport()");
        return null;
    }

    public gPayload createPipelineNodes(gPayload gpay) {
        logger.error("gPayload createPipelineNodes(gPayload gpay)");
        return null;
    }

    public boolean setPipelineStatus(String pipelineId, String status_code, String status_desc) {
        logger.error("boolean setPipelineStatus(String pipelineId, String status_code, String status_desc)");
        return false;
    }

    public gPayload getPipelineObj(String pipelineId) {
        logger.error("gPayload getPipelineObj(String pipelineId)");
        return  null;
    }

    public String addINodeResource(String resource_id, String inode_id) {
        logger.error("String addINodeResource(String resource_id, String inode_id)");
        return null;
    }

    public String getNodeId(String region, String agent, String plugin) {
        logger.error("String getNodeId(String region, String agent, String plugin)");
        return  null;
    }

    public int getPipelineStatusCode(String pipelineId) {
        logger.error("int getPipelineStatusCode(String pipelineId)");
        return -1;
    }

    public int getINodeStatus(String INodeId) {
        logger.error("int getINodeStatus(String INodeId)");
        return -1;
    }

    public boolean removePipeline(String pipelineId) {
        logger.error("boolean removePipeline(String pipelineId)");
        return false;
    }

    public List<String> getNodeList(String region, String agent, String plugin) {
        logger.error("List<String> getNodeList(String region, String agent, String plugin)");
        return null;
    }

    public String addIsAttachedEdge(String resource_id, String inode_id, String region, String agent, String plugin) {
        logger.error("String addIsAttachedEdge(String resource_id, String inode_id, String region, String agent, String plugin)");
        return null;
    }

    public String getResourceEdgeId(String resource_id, String inode_id) {
        logger.error("String getResourceEdgeId(String resource_id, String inode_id)");
        return null;
    }
    public String getIsAssignedParam(String edge_id,String param_name) {
        logger.error("String getIsAssignedParam(String edge_id,String param_name)");
        return null;
    }

    public boolean setDBImport(String exportData) {
        logger.error("boolean setDBImport(String exportData)");
        return false;
    }



}
