package assignment7;


interface SupportHandler {
    void handleRequest(SupportRequest request);

    void setNextHandler(SupportHandler softwareHandler);
}

class HardwareSupportHandler implements SupportHandler {
    private SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(SupportRequest request) {
        if (request.getType() == SupportRequest.Type.HARDWARE) {
            System.out.println("Hardware support team is handling the request: " + request.getDescription());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("No handler available to handle the request: " + request.getDescription());
        }
    }
}

class SoftwareSupportHandler implements SupportHandler {
    private SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(SupportRequest request) {
        if (request.getType() == SupportRequest.Type.SOFTWARE) {
            System.out.println("Software support team is handling the request: " + request.getDescription());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("No handler available to handle the request: " + request.getDescription());
        }
    }
}

class NetworkSupportHandler implements SupportHandler {
    private SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(SupportRequest request) {
        if (request.getType() == SupportRequest.Type.NETWORK) {
            System.out.println("Network support team is handling the request: " + request.getDescription());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("No handler available to handle the request: " + request.getDescription());
        }
    }
}

class HelpDesk {
    private SupportHandler handlerChain;

    public HelpDesk() {

        SupportHandler networkHandler = new NetworkSupportHandler();
        SupportHandler softwareHandler = new SoftwareSupportHandler();
        SupportHandler hardwareHandler = new HardwareSupportHandler();

        networkHandler.setNextHandler(softwareHandler);
        softwareHandler.setNextHandler(hardwareHandler);

        handlerChain = networkHandler;
    }

    public void handleRequest(SupportRequest request) {
        handlerChain.handleRequest(request);
    }
}

class SupportRequest {
    enum Type { HARDWARE, SOFTWARE, NETWORK }

    private final Type type;
    private final String description;

    public SupportRequest(Type type, String description) {
        this.type = type;
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}


public class Assignment7Ex1 {
    public static void main(String[] args) {
        HelpDesk helpDesk = new HelpDesk();

        helpDesk.handleRequest(new SupportRequest(SupportRequest.Type.NETWORK, "Network connectivity issue"));
        helpDesk.handleRequest(new SupportRequest(SupportRequest.Type.SOFTWARE, "Application crashing"));
        helpDesk.handleRequest(new SupportRequest(SupportRequest.Type.HARDWARE, "Printer not working"));
    }
}

// handlers are organized in a HelpDesk class and are organized in a hierarchical order there
// SupportRequest class represents request support with their description