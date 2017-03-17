import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFrame;

import java.awt.event.ActionListener;

//TODO: possibly implement key shortcuts?

public class AppMenuBar extends JMenuBar{

  private static final String[] MENUS = {
    "Homework",
    "Evaluations",
    "Organization",
    "Other"
  };
  
  private static final String[] ITEM_NAMES = {
    "Manage",
    "New",
    "View By..."
  };
  
  private static final String[] ITEM_COMMANDS = {
    "table",
    "new",
    "subform"
  };
  
  public AppMenuBar(ActionListener owner){
    JMenu changeClassMenu = new JMenu("Change Class");
    
    JMenuItem chooseExistingClassItem = new JMenuItem("Choose from Existing...");
    chooseExistingClassItem.setActionCommand("choose-class");
    chooseExistingClassItem.addActionListener(owner);
    
    JMenuItem createNewClassItem = new JMenuItem("New");
    createNewClassItem.setActionCommand("new-class");
    createNewClassItem.addActionListener(owner);
    
    changeClassMenu.add(chooseExistingClassItem);
    changeClassMenu.add(createNewClassItem);
    
    
    add(changeClassMenu);
    
    for (String menu: MENUS){
      JMenu menuOption = new JMenu(menu);
      for (int i = 0; i < ITEM_NAMES.length; i++){
        JMenuItem menuItem = new JMenuItem(ITEM_NAMES[i]);
        
        menuItem.setActionCommand(ITEM_COMMANDS[i]+"-"+menu.toLowerCase());
        menuItem.addActionListener(owner);
       
        menuOption.add(menuItem);
      }
      add(menuOption);
    }
  }
}