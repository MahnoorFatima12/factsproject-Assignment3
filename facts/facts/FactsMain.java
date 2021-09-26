import javax.swing.*;  

public class FactsMain {
    
    private static final String jsFile = "C:/Users/MF/Desktop/CS4367/factsAssignment2/src/facts/data/facts.js";
    private static final String xmlFile = "C:/Users/MF/Desktop/CS4367/factsAssignment2/src/facts/data/facts.xml";
    private facts.FactList list;
    
    GuiFrame gFrame = new GuiFrame(this);

    public FactsMain() {
        facts.Parser parser = new facts.Parser(xmlFile);
	list = parser.getFactList();
        
        nextFact();
        
        gFrame.setVisible(true);
    }
    
    public void nextFact()
    {
        facts.Fact temp = list.getRandom();
        gFrame.nextFact(temp.getText(), temp.getAuthor());
    }
    
    void search(String searchText, String searchMode) {
        if (searchText != null && !searchText.equals("")){  // Received a search request
                int searchModeVal = facts.FactSearchMode.ALL_VAL; // Default
                if (searchMode != null && !searchMode.equals("")){  // If no parameter value, let it default.
                        if (searchMode.equalsIgnoreCase("text")){
                                searchModeVal = facts.FactSearchMode.TEXT_VAL;
                        } else if (searchMode.equalsIgnoreCase("author")){
                                searchModeVal = facts.FactSearchMode.AUTHOR_VAL;
                        } else if (searchMode.equalsIgnoreCase("type")){
                                searchModeVal = facts.FactSearchMode.TYPE_VAL;
                        } 
                }
                facts.FactList results = list.search(searchText, searchModeVal);
                facts.Fact temp;
                if (results.getSize() == 0){
                        gFrame.searchedFact("No Record Found");
                }else{
                    String result = "";
                    for (int i = 0; i < results.getSize() ; i++){
                        temp = results.get(i);
                        
                        result += "<b>" + temp.getText() + "<b><br>" + temp.getAuthor() + "<br>" + temp.getType() + "<br><br>";
                        
                        
                    }
                    gFrame.searchedFact(result);
                }
                
        }
    }
    
    
    
    public static void main(String args[]) {
       FactsMain facts = new FactsMain();
       
          
    }

    
    
}
