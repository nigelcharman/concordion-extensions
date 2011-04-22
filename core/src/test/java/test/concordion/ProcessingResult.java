package test.concordion;

import nu.xom.Document;

import org.concordion.api.Element;
import org.concordion.api.ResultSummary;
import org.concordion.api.listener.AssertFailureEvent;
import org.concordion.internal.XMLParser;

public class ProcessingResult {

    private final ResultSummary resultSummary;
    private final EventRecorder eventRecorder;
    private final String documentXML;

    public ProcessingResult(ResultSummary resultSummary, EventRecorder eventRecorder, String documentXML) {
        this.resultSummary = resultSummary;
        this.eventRecorder = eventRecorder;
        this.documentXML = documentXML;
    }
    
    public long getSuccessCount() {
        return resultSummary.getSuccessCount();
    }

    public long getFailureCount() {
        return resultSummary.getFailureCount();
    }

    public long getExceptionCount() {
        return resultSummary.getExceptionCount();
    }
    
    public AssertFailureEvent getLastAssertFailureEvent() {
        return (AssertFailureEvent) eventRecorder.getLast(AssertFailureEvent.class);
    }

    public Document getXOMDocument() {
        try {
            return XMLParser.parse(documentXML);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse resultant XML document", e);
        }
    }
    
    public Element getRootElement() {
        return new Element(getXOMDocument().getRootElement());
    }
    
    public String getFooterText() {
        Element[] childDivs = getRootElement().getDescendantElements("div");
        for (Element div : childDivs) {
            if ("footer".equals(div.getAttributeValue("class"))) {
                return div.getText();
            }
        }
        return "";
    }
    
    public String toXML() {
        return getRootElement().toXML();
    }

    public String successOrFailureInWords() {
        return hasFailures()  ? "FAILURE" : "SUCCESS";
    }

    public boolean hasFailures() {
        return getFailureCount() + getExceptionCount() != 0;
    }

    public boolean isSuccess() {
        return !hasFailures();
    }

    private Element getOutputFragment() {
        return getRootElement().getFirstDescendantNamed("fragment");
    }

    public String getOutputFragmentXML() {
        return getOutputFragment().toXML().replaceAll("</?fragment>", "").replaceAll("\u00A0", "&#160;");
    }

    public boolean hasCSSDeclaration(String cssFilename) {
        Element head = getHeadElement();
        for (Element link : head.getChildElements("link")) {
            String href = link.getAttributeValue("href");
            String type = link.getAttributeValue("type");
            String rel = link.getAttributeValue("rel");
            if (cssFilename.equals(href) 
                    && "text/css".equals(type)
                    && "stylesheet".equals(rel)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEmbeddedCSS(String css) {
        for (Element style : getHeadElement().getChildElements("style")) {
            if (style.getText().contains(css) ) {
                return true;
            }
        }
        return false;
    }

    public boolean hasJavaScriptDeclaration(String jsFilename) {
        for (Element script : getHeadElement().getChildElements("script")) {
            String type = script.getAttributeValue("type");
            String src = script.getAttributeValue("src");
            if ("text/javascript".equals(type) && jsFilename.equals(src)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEmbeddedJavaScript(String javaScript) {
        for (Element script : getHeadElement().getChildElements("script")) {
            String type = script.getAttributeValue("type");
            if ("text/javascript".equals(type) && script.getText().contains(javaScript)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasJavaScriptFunction(String functionName) {
        return hasEmbeddedJavaScript("function " + functionName + "(");
    }
    
    private Element getHeadElement() {
        return getRootElement().getFirstChildElement("head");
    }
}
