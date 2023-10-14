public class ProcessInfo {
    private int processId;
    private String imageName;
    private String sessionName;
    private String memoryUsage;
    private int sessionNumber;

    public ProcessInfo() {
    }

    public ProcessInfo(String lineData) {
        //tách chuỗi
        String data[] = lineData.split("\\s+") ;
        imageName = data[0];
        processId =Integer.parseInt( data[1]);
        sessionName = data[2];
        sessionNumber =Integer.parseInt( data[3]);
        memoryUsage = data[4];

    }

    public ProcessInfo(int processId, String imageName, String sessionName, String memoryUsage) {
        this.processId = processId;
        this.imageName = imageName;
        this.sessionName = sessionName;
        this.memoryUsage = memoryUsage;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(String memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }
}
