package Schematicomatic;

/** SchematicomaticView class
 * The view for the Schematic-o-matic program. Displays all of the parts, the layout,and
 * the simulation of the schematic.
 * Date Created: 24/12/2012
 * Date Last Modified: 20/01/2013
 * @author William Ma, Theodore Tan, Ryan Hashmati
 */ 
import java.awt.*;
import java.util.*;
import javax.swing.*;
import Circuit.*;
import java.io.*;

public class SchematicomaticView extends JPanel
{   
    SchematicomaticModel model;     //The model of the schematic program
    
    private JFileChooser chooser = new JFileChooser(); //File chooser to save the chart
    private PrintWriter savePrinter;    //The out file to save the schematic to
    private Scanner loader;             //The in file to load a schematic
    
    private BorderLayout baseBorderLayout = new BorderLayout(); //Border Layout for the base panel
    private BorderLayout topBarBorderLayout = new BorderLayout(); // Border Layout for the top panel
    private JPanel topBarPanel = new JPanel(); //Panel to be used at the top of the screen
    private JPanel schematicPanel = new JPanel(); //Panel for all the circuit parts
    private  JPanel bottomBarPanel = new JPanel(); //Panel for the bottom of the screen
    private JPanel buttonsPanel = new JPanel(); //Panel for the buttons that create circuit parts
    private JPanel searchPanel = new JPanel(); //Panel with the search bar
    private JPanel simulatePanel = new JPanel(); //Panel with the simulate button
    private JButton gates = new JButton(); //Button to display the gates
    private JButton transistors = new JButton(); //Button to display the transistors
    private JButton capacitors = new JButton(); //Button to display the capacitors
    private JButton resistors = new JButton(); // Button to display the resistors
    private JButton powerSupplies = new JButton(); // Button to display the power supplies
    private JButton diodes = new JButton(); //Button to display the diodes
    private JButton switches = new JButton(); //Button to display the switches
    private JButton ics = new JButton(); // Button to display the ICs
    private  JButton wire = new JButton(); //Button to display the wire
    private JButton deleteComponent = new JButton(); //Button to enter delete mode
    private JButton backButton = new JButton(); //Button to return to the main buttons panel
    private JButton andGate = new JButton(); //Creates and gate part
    private JButton norGate = new JButton(); //Creates nor gate part
    private JButton orGate = new JButton(); //Creates or gate part
    private JButton notGate = new JButton(); //Creates nor gate part
    private  JButton xorGate = new JButton(); //Creates xor gate aprt
    private  JButton nandGate = new JButton(); //Creates nand gate part
    private  JButton flipFlop = new JButton(); //creates flip flop part
    private JButton npn = new JButton(); //Creates npn transistor
    private JButton pnp = new JButton(); //Creates pnp trasistor
    private  JButton oneMF = new JButton(); //Creates oneMF capacitor
    private JButton tenMF = new JButton(); //Creates tenMf capacitor
    private  JButton oneHundredMF = new JButton(); //Creates one hundred mf capacitor
    private JButton createCapacitor = new JButton("Custom Capacitor"); // Creates custom capacitor
    private JButton createResistor = new JButton("Custom Resistor"); //Creates custom resistor
    private  JButton createBattery = new JButton("Custom Battery"); //Creates custom battery 
    private  JButton oneKRes = new JButton(); //Creates one k resistor
    private JButton tenKRes = new JButton(); //Creates ten k resistor
    private JButton fourSeventyRes = new JButton(); //Creates 470 resistor
    private JButton plusBattery = new JButton(); //Creates a positive batter
    private JButton ground = new JButton(); //Creates a ground part
    private JButton greenLED = new JButton(); //Creates a green led
    private JButton redLED = new JButton(); //Creates a red led
    private JButton yellowLED = new JButton(); //Creates a yellow led
    private JButton blueLED = new JButton(); //Creates a blue led
    private JButton diode = new JButton(); //Creates a diode
    private JButton tradSwitch = new JButton(); //Creates a traditional switch
    private JButton plusSwitch = new JButton(); //Creates a positive switch
    private JButton negSwitch = new JButton(); //Creates a negitive switch
    private  JButton hBridge = new JButton(); //Creates a h-bridge part
    private JButton pic24 = new JButton(); //Creates a pic24 ic part
    private JButton blackWire = new JButton(); //Creates black wire
    private JButton redWire = new JButton(); //Creates red wire
    private JButton blueWire = new JButton(); //Creates blue wire
    private JButton yellowWire = new JButton(); //Creates yellow wire
    private JButton greenWire = new JButton(); //Creates green wire
    private JButton purpleWire = new JButton(); //creates purple wire
    private JButton orangeWire = new JButton(); //Creates orange wire
    private JButton rotate = new JButton(); //Rotates part
    private JButton highlight = new JButton("Highlight"); //Starts highlight mode
    private JButton unHighlight = new JButton("Un-Highlight"); //Starts un-highlight mode
    private JButton exportParts = new JButton("Bill of Materials Export"); //Exports bill of materials
    private JButton save = new JButton("Save"); //Saves the current schematic
    private JButton load = new JButton("Load"); //Loads a saved schematic
    private JTextField input = new JTextField(6); //Text field for custom input    
    private JButton stop = new JButton(); //Stop button
    private JButton stop2 = new JButton("Stop"); //Stop Button
    private JButton clearAll = new JButton ("Clear All"); //Clears the entire schematic
    private JButton yes = new JButton("YES"); //Confirmation
    private JButton no = new JButton("NO"); //Confirmation
    private JButton startStopSimulate = new JButton("Start"); //Start/stop simulation button
    private JTextField searchBar = new JTextField(7); //Search bar
    private JButton searchButton = new JButton(); //Search Button
    private  ArrayList <JButton> buttonList = new ArrayList <JButton>(); //Array list of all buttons

    private ArrayList <Drawing> list = new ArrayList <Drawing>(); //Arraylist of drawings
    private ArrayList <Wire> list3 = new ArrayList<Wire>();   //Arraylist of wire
    
    private JPanel highlighter; //Panel for highlighter buttons
    private JPanel stopH; //
    private JPanel inputPanel;
    
    private MouseController mouse1; //Mouse
    private MouseController2 mouse2; //Mouse
    
    private int wireIncrement = 0; //Pieces of wire
    private ArrayList <Integer> deletedIncrement = new ArrayList <Integer> (); //Deleted pieces of wire
    
    private int numPic, numHBridge; //numbers of pics and hbridge ics
    
    private JLabel warning = new JLabel();  //Warning that there is a short circuit
    private JLabel confirm = new JLabel();  //Confirmation warning to the clear all function
    
    private JButton easterEgg = new JButton();
    
    //Change switch drawings to JButtons when simulate starts
    private ArrayList <JButton> switchButtonList = new ArrayList <JButton>();
    private JButton tradSwitchButton = new JButton();
    private JButton posSwitchButton = new JButton();
    private JButton negSwitchButton = new JButton();       
    private SwitchChangeController changeController;    
    
    /**Constructor for the Schematicomatic View. Sets the layout, registers all the controllers ,
     * Sets the tool tips for all the buttons, sets the buttons  also sets the action commands for all the buttons
     * @param m Instance of the Main model
     */
    public SchematicomaticView (SchematicomaticModel m)
    {
        super();
        model = m;
        //Lays out the program screen
        this.layoutView();
        //Registers all program controllers so it would respond to user input
        this.registerControllers();
        //Set tooltips of all buttons so user can easilly see what part is waht
        this.setToolTips();   
        //Set all the action command of all the buttons because right now their command
        //is ""
        this.setActionCommandsAllButtons();
        
        //Set some of the button texts to these
        gates.setText("Gates");
        transistors.setText("Transistors");
        capacitors.setText("Capacitors");
        resistors.setText("Resisors");
        powerSupplies.setText("Power");
        diodes.setText("Diodes");
        switches.setText("Switches");
        ics.setText("ICs");
        wire.setText("Wire");
        deleteComponent.setText("Delete");
        backButton.setText("Back");
        searchButton.setText("Go");
        input.setText("Input value");
        stop.setText("Stop");
        
        //Puts icons into all the buttons
        this.setButtonIcons();
    }
    
    /**Resets the buttons panel to the main selection of buttons
     */
    public void resetToMain()
    {
        hideAll();
        showMain(true);
    }
    
    /**Sets the layout of the main screen
     */
    public void layoutView()
    {
        //Set the main layout to a borderlayout
        this.setLayout(baseBorderLayout);
        
        //Add the top panel, schematic panel, and bottom panel to the right sides
        this.add(topBarPanel, BorderLayout.NORTH);
        this.add(schematicPanel, BorderLayout.CENTER);
        this.add(bottomBarPanel, BorderLayout.SOUTH);
        
        Font font = new Font("Ariel", Font.BOLD, 20);
        
        //Places the warning at the bottom of the screen to warn the user that there
        //is a short circuit. Keeps it hidden at first and will be used later
        warning.setFont(font);
        warning.setText("WARNING: There is a short circuit somewhere on your circuit");
        schematicPanel.add(warning);
        //Gets the height and width of the screen and centers it at the bottom
        //Helpful because it will work on different computers with different screen
        //sizes and resolutions
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        warning.setBounds(dimension.width/2-300, dimension.height-200, 600, 20);
        warning.setVisible(false);   
        
        //Confirmation added at the bottom of the screen to make sure the user
        //really wants to clear everything. Keeps it hidden at first and will be used later
        confirm.setFont(font);
        confirm.setText("Are you sure you want to delete everything?");
        schematicPanel.add(confirm);
        schematicPanel.add(yes);
        schematicPanel.add(no); 
        //Just like warning, sets the location based on screen width and height
        //for better transistions between computers
        confirm.setBounds(dimension.width/2-220, dimension.height-350, 440, 25);
        yes.setBounds(dimension.width/2-220, dimension.height-310, 170, 30);
        no.setBounds(dimension.width/2+50, dimension.height-310, 170, 30);
        confirm.setVisible(false);
        yes.setVisible(false);
        no.setVisible(false);
        
        //Sets the main panel layout to be null so drag and drop would work better
        schematicPanel.setLayout(null);
        //sets the background to white so the user knows exactly how big the panel is
        schematicPanel.setBackground(Color.WHITE);
        
        //Set the top panel to a new border layout adding the buttons panel at the
        //center and the search panel to the east
        topBarPanel.setLayout(topBarBorderLayout);
        topBarPanel.add(buttonsPanel, BorderLayout.CENTER);
        topBarPanel.add(searchPanel, BorderLayout.EAST);        
        
        //Creates a new highlighter panel to be placed at the bottom of the screen
        //This panel includes highlight and unhighlight buttons
        highlighter = new JPanel();
        highlighter.setLayout(new GridLayout(1,2));
        highlighter.add(highlight);
        highlighter.add(unHighlight);
        highlighter.setBorder(BorderFactory.createTitledBorder("Highlighter"));        
        
        //Creates a new rotate panel to be placed at the bottom of the screen
        //This panel includes the rotate button
        JPanel rotater = new JPanel();
        rotater.setLayout(new BorderLayout());
        rotater.add(rotate, BorderLayout.CENTER);
        
        //Creates a new exporter panel to be placed at the bottom of the screen
        //This panel includes the export to bill of materials, save, and load buttons
        JPanel exporter = new JPanel();
        exporter.setLayout(new GridLayout(1,3));
        exporter.add(exportParts);
        exporter.add(save);
        exporter.add(load);
        
        //Creates a new stop highlighting panel to be plaed at the bottom of the screen
        //This panel includes the stop button to stop highlighting or unhighlighting
        stopH = new JPanel();
        stopH.setLayout(new BorderLayout());
        stopH.add(stop2, BorderLayout.CENTER);
        
        //Sets borders onto the panels and also gives the rotate button a rotate icon
        stopH.setBorder(BorderFactory.createTitledBorder("Stop"));
        rotater.setBorder(BorderFactory.createTitledBorder("Rotate"));
        exporter.setBorder(BorderFactory.createTitledBorder("Save/Load"));
        rotate.setIcon(new ImageIcon("Resorces/Rotate.png"));
        
        //Creates a new clear all panel to be placed at the bottom of the screen
        //this panel includes the clear all button to clear the whole schematic
        JPanel clearAller = new JPanel();
        clearAller.setLayout(new BorderLayout());
        clearAller.add(clearAll, BorderLayout.CENTER);        
        clearAller.setBorder(BorderFactory.createTitledBorder("Clear Schematic"));
        
        //Makes the startStopSimulate button take up its maximum space and creates
        //a border around it that says Simulate
        simulatePanel.setLayout(new BorderLayout());
        simulatePanel.add(startStopSimulate, BorderLayout.CENTER);
        simulatePanel.setBorder(BorderFactory.createTitledBorder("Simulate"));
        
        //Sets the bottom bar panel to a new gridbaglayout
        bottomBarPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //Fills any remaining space
        c.fill = GridBagConstraints.BOTH;
        
        //set the weight to 1, the smallest part of the bottombar panel
        //and add the simulatePanel and rotater panel
        c.weightx = 1;  
        bottomBarPanel.add(simulatePanel, c);
        bottomBarPanel.add(rotater, c);
        //set the weight to 3 so it will take up 3 times as much space as the
        //simulate or rotate panel. Add the highlighter and stop highlight panel
        //set the visibility of the stop panel to false because it will only be on
        //when the highlighter/unhighlighter is selected
        c.weightx = 3;
        bottomBarPanel.add(highlighter, c);
        bottomBarPanel.add(stopH, c);
        stopH.setVisible(false);
        //Set the weight back to 1 to add the clear all button
        c.weightx = 1;
        bottomBarPanel.add(clearAller, c);
        //set the weight to 2 to add the save/load panel
        c.weightx = 2;
        bottomBarPanel.add(exporter, c);  
        
        buttonsPanel.add(this.easterEgg); //...did you have to make the name so obvious??
        
        //add all of the inner category buttons to the arraylilst of buttons to make
        //the search part easier to code
        addButtonsToList();
        //Add all of the category buttons to the buttons panel
        buttonsPanel.add(this.gates);
        buttonsPanel.add(this.ics);
        buttonsPanel.add(this.capacitors);
        buttonsPanel.add(this.switches);
        buttonsPanel.add(this.resistors);
        buttonsPanel.add(this.transistors);
        buttonsPanel.add(this.diodes);
        buttonsPanel.add(this.powerSupplies);
        buttonsPanel.add(this.wire);
        
        //for each button in the arraylist of buttons, add it to the buttons panel
        for(JButton temp: buttonList){
            buttonsPanel.add(temp);
        }
        
        //Creates a new input panel that holds the input for creating custom parts
        //Create a gridbaglayout for it.
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.BOTH;
        //Add all of the input buttons and the input textfield to the panel
        c.weightx = 5;
        inputPanel.add(input, c2);
        inputPanel.add(createBattery, c2);
        inputPanel.add(createResistor, c2);
        inputPanel.add(createCapacitor, c2);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Create Custom Part"));
        
        //add the input panel to the buttons panel and also add the backbutton
        //as the final button to add so it will always show up to the right of every single button
        buttonsPanel.add(inputPanel);
        buttonsPanel.add(backButton);
        
        buttonsPanel.setVisible(true);
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Parts"));
        
        //Add searchbar and searchbutton to the search panel and set the border of it
        searchPanel.add(searchBar);
        searchPanel.add(searchButton);
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Here"));
        
        //Call resetToMain() so all buttons will be hidden except for the buttons 
        //on the main panel
        resetToMain();
    }
    
    /**
     * Set the tool tips for all the buttons so when the mouse is hovered over the
     * button, the user will know what part the button will create.
     */
    private void setToolTips (){
        andGate.setToolTipText("AND Gate");
        orGate.setToolTipText("OR Gate");
        xorGate.setToolTipText("XOR Gate");
        norGate.setToolTipText("NOR Gate");
        notGate.setToolTipText("NOT Gate");
        nandGate.setToolTipText("NAND Gate");
        flipFlop.setToolTipText("Clocked Flip Flop");
        npn.setToolTipText("NPN Transistor");
        pnp.setToolTipText("PNP Transistor");
        oneMF.setToolTipText("Capacitor 1uF");
        tenMF.setToolTipText("Capacitior 10uF");
        oneHundredMF.setToolTipText("Capcitor 100uF");
        createCapacitor.setToolTipText("Create Custom Capacitor");
        createResistor.setToolTipText("Create Custom Resistor");
        createBattery.setToolTipText("Create Custom Battery");
        oneKRes.setToolTipText("Resistor 1kΩ");
        tenKRes.setToolTipText("Resistor 10kΩ");
        fourSeventyRes.setToolTipText("Resistor 470Ω");
        plusBattery.setToolTipText("6V Battery");
        ground.setToolTipText("Ground");
        greenLED.setToolTipText("Green LED (off)");
        redLED.setToolTipText("Red LED (off)");
        yellowLED.setToolTipText("Yellow LED (off)");
        blueLED.setToolTipText("Blue LED (off)");
        diode.setToolTipText("Diode (off)");
        tradSwitch.setToolTipText("Traditional Switch");
        plusSwitch.setToolTipText("Positive Switch");
        negSwitch.setToolTipText("Negitive Switch");
        hBridge.setToolTipText("H-Bridge");
        pic24.setToolTipText("Pic24");
        blackWire.setToolTipText("Black Wire");
        yellowWire.setToolTipText("Yellow Wire");
        greenWire.setToolTipText("Green Wire");
        purpleWire.setToolTipText("Purple Wire");
        orangeWire.setToolTipText("Orange Wire");
        easterEgg.setToolTipText("Dark Voice");
    }
    
    /**
     * Gets integer in the custom value text field
     * @return the integer in the custom value text field
     */
    public int getInput()
    {
        int text = Integer.parseInt(input.getText());
        input.setText("Input value");
        return text;
    }
    
    /**
     * Register all controllers for buttons and mouse listeners
     */
    public void registerControllers()
    {     
        //Add a mouselistener and a mousemotionlistener to the schematicpanel
        mouse2 = new MouseController2(this, this.model);
        schematicPanel.addMouseListener(mouse2);
        
        mouse1 = new MouseController(this, mouse2);
        schematicPanel.addMouseMotionListener(mouse1);
        
        //Main panel buttons
        CategoryController categories = new CategoryController(this);
        gates.addActionListener(categories);
        ics.addActionListener(categories);
        capacitors.addActionListener(categories);
        switches.addActionListener(categories);
        resistors.addActionListener(categories);
        wire.addActionListener(categories);
        diodes.addActionListener(categories);
        powerSupplies.addActionListener(categories);
        transistors.addActionListener(categories);
        
        //Gates panel buttons
        GatesController gateTpyes = new GatesController (this);
        andGate.addActionListener(gateTpyes);
        norGate.addActionListener(gateTpyes);
        orGate.addActionListener(gateTpyes);
        notGate.addActionListener(gateTpyes);
        xorGate.addActionListener(gateTpyes);
        nandGate.addActionListener(gateTpyes);
        flipFlop.addActionListener(gateTpyes);
        
        //Transistor panel buttons
        TransistorController transistorTypes = new TransistorController(this);
        npn.addActionListener(transistorTypes);
        pnp.addActionListener(transistorTypes);
        
        //Capacitor panel buttons
        CapacitorController capacitorTypes = new CapacitorController(this);
        oneMF.addActionListener(capacitorTypes);
        tenMF.addActionListener(capacitorTypes);
        oneHundredMF.addActionListener(capacitorTypes);
        createCapacitor.addActionListener(capacitorTypes);
        
        //Power panel buttons
        PowerController powerTypes = new PowerController(this);
        plusBattery.addActionListener(powerTypes);
        ground.addActionListener(powerTypes);
        createBattery.addActionListener(powerTypes);
        
        //Diode panel buttons
        DiodeController diodeTypes = new DiodeController(this);
        greenLED.addActionListener(diodeTypes);
        redLED.addActionListener(diodeTypes);
        yellowLED.addActionListener(diodeTypes);
        blueLED.addActionListener(diodeTypes);
        diode.addActionListener(diodeTypes);
        
        //Switch panel buttons
        SwitchController switchTypes = new SwitchController (this);
        tradSwitch.addActionListener(switchTypes);
        plusSwitch.addActionListener(switchTypes);
        negSwitch.addActionListener(switchTypes);
        
        //ICs panel buttons
        ICsController icTypes = new ICsController(this);
        hBridge.addActionListener(icTypes);
        pic24.addActionListener(icTypes);
        
        //Wire panel buttons
        WireController wireTypes = new WireController(mouse1, mouse2, this);
        blackWire.addActionListener(wireTypes);
        blueWire.addActionListener(wireTypes);
        redWire.addActionListener(wireTypes);
        yellowWire.addActionListener(wireTypes);
        greenWire.addActionListener(wireTypes);
        purpleWire.addActionListener(wireTypes);
        orangeWire.addActionListener(wireTypes);
        
        //Back button
        BackController back = new BackController (this);
        backButton.addActionListener(back);
        
        //Search button
        SearchController searchController = new SearchController(this, searchBar);
        searchButton.addActionListener(searchController);
        
        //Delete button
        DeleteController deleteController = new DeleteController(mouse1, mouse2, this);
        deleteComponent.addActionListener(deleteController);
        
        //Stop button
        StopController stopController = new StopController(mouse1, mouse2, this);
        stop.addActionListener(stopController);
        stop2.addActionListener(stopController);
        
        //Simulate button
        SimulateController simulateController = new SimulateController(this, this.model);
        startStopSimulate.addActionListener(simulateController);
        
        //Resistor panel buttons
        ResistorController resistorTypes = new ResistorController(this);
        oneKRes.addActionListener(resistorTypes);
        tenKRes.addActionListener(resistorTypes);
        fourSeventyRes.addActionListener(resistorTypes);  
        createResistor.addActionListener(resistorTypes);
        
        //Rotate button
        RotateController rotateController = new RotateController(this, mouse2);
        rotate.addActionListener(rotateController);
        
        //EscapeController will come in a further patch... will allow user
        //to hit the esc key while drawing wire to get rid of their first point
        //if they hit the wrong spot
        EscapeController escapeController = new EscapeController(this, mouse2);
        schematicPanel.addKeyListener(escapeController);
//        schematicPanel.setFocusable(true);
//        schematicPanel.requestFocus();
//        schematicPanel.requestFocus(true);
//        schematicPanel.
        
        //Highlight buttons
        HighlighterController highlighterController = new HighlighterController(mouse1, mouse2, this);
        highlight.addActionListener(highlighterController);
        unHighlight.addActionListener(highlighterController);
        
        //Export button
        ExportToChartController exportController = new ExportToChartController(model, this);
        exportParts.addActionListener(exportController);
        
        //Save and load button
        SaveLoadController saveLoadController = new SaveLoadController(this);
        save.addActionListener(saveLoadController);
        load.addActionListener(saveLoadController);
        
        //switch button for the switch drawings
        changeController = new SwitchChangeController(this);
        
        //Text mouse controller to highlight the word in the input box when clicked
        TextController textController = new TextController(this, input);
        input.addMouseListener(textController);
        
        //Clear all button with the yes and no
        ClearAllController clearAllController = new ClearAllController(model, this);
        clearAll.addActionListener(clearAllController);
        yes.addActionListener(clearAllController);
        no.addActionListener(clearAllController);
    }
  
    /**
     * Displays the main selection of buttons
     * @param on state of the buttons visibility
     */
    public void showMain(boolean on)
    {
        gates.setVisible(on);
        ics.setVisible(on);
        capacitors.setVisible(on);
        switches.setVisible(on);
        resistors.setVisible(on);
        wire.setVisible(on);
        powerSupplies.setVisible(on);
        diodes.setVisible(on);
        deleteComponent.setVisible(on);
        transistors.setVisible(on);
    }
    
    /**
     * Displays the gate selection of buttons
     * @param on state of the buttons visibility
     */
    public void showGates (boolean on)
    {
        andGate.setVisible(on);
        norGate.setVisible(on);
        orGate.setVisible(on);
        notGate.setVisible(on);
        xorGate.setVisible(on);
        nandGate.setVisible(on);
        flipFlop.setVisible(on);
        backButton.setVisible(on);
    }
    
    /**
     * Displays the transistor selection of buttons
     * @param on state of the buttons visibility
     */
    public void showTransistors (boolean on)
    {
        npn.setVisible(on);
        pnp.setVisible(on);
        backButton.setVisible(on);
    }
    
    /**
     * Displays the capacitor selection of buttons
     * @param on state of the buttons visibility
     */
    public void showCapacitors (boolean on)
    {
        oneMF.setVisible(on);
        tenMF.setVisible(on);
        oneHundredMF.setVisible(on);
        inputPanel.setVisible(on);
        createCapacitor.setVisible(on);
        backButton.setVisible(on);
    } 
    
    /**
     * Displays the resistor selection of buttons
     * @param on state of the buttons visibility
     */
    public void showResistors(boolean on)
    {
        
        oneKRes.setVisible(on);
        tenKRes.setVisible(on);
        fourSeventyRes.setVisible(on);
        inputPanel.setVisible(on);
        createResistor.setVisible(on);
        backButton.setVisible(on);
    }
    
    /**
     * Displays the Power supplies selection of buttons
     * @param on state of the buttons visibility
     */
    public void showPowerSupplies(boolean on)
    {
        plusBattery.setVisible(on);
        ground.setVisible(on);
        inputPanel.setVisible(on);
        createBattery.setVisible(on);
        backButton.setVisible(on);
    }
    
    /**
     * Displays the diode selection of buttons
     * @param on state of the buttons visibility
     */
    public void showDiodes (boolean on)
    {
        greenLED.setVisible(on);
        redLED.setVisible(on);
        yellowLED.setVisible(on);
        blueLED.setVisible(on);
        diode.setVisible(on);
        backButton.setVisible(on);
    }
    
    /**
     * Displays the switches selection of buttons
     * @param on state of the buttons visibility
     */
    public void showSwitches (boolean on)
    {
        tradSwitch.setVisible(on);
        plusSwitch.setVisible(on);
        negSwitch.setVisible(on);
        backButton.setVisible(on);
    }
    
    /**
     * Displays the IC selection of buttons
     * @param on state of the buttons visibility
     */
    public void showICS (boolean on){
        hBridge.setVisible(on);
        pic24.setVisible(on);
        backButton.setVisible(on);
    }
    
    /**
     * Displays the wire selection of buttons
     * @param on state of the buttons visibility
     */
    public void showWire(boolean on)
    {
        blackWire.setVisible(on);
        redWire.setVisible(on);
        blueWire.setVisible(on);
        yellowWire.setVisible(on);
        greenWire.setVisible(on);
        purpleWire.setVisible(on);
        orangeWire.setVisible(on);
        backButton.setVisible(on);
    }
    
    /**
     * Displays the buttons being searched for.
     * @param part String being searched for
     */
    public void showSearch(String part)
    {
        hideAll();
        boolean flag = false;
        //convert the string to lower case so it will ignore case
        part = part.toLowerCase();
        
        //if the user searched nothing, only show the backbutton and return
        if (part.equals(""))
        {
            backButton.setVisible(true);
            return;
        }
        
        //Dont worry about this
        else if (part.equals("starcraft"))
        {
            easterEgg.setVisible(true);
            backButton.setVisible(true);
            return;
        }
        
        //Look through each button in the button list to see if it starts with
        //the string. If it does, set that button to be visible and make the flag
        //equal to true for the next part
        for(JButton counter: buttonList)
        {
            if(counter.getActionCommand().startsWith(part))
            {          
                counter.setVisible(true);
                flag = true;
            }
        }

        //If the flag is true, there is no need to search for contains because
        //the word the user searched is probably one or two letters long
        //If flag is false, look through each button in the list to see if it 
        //contains the word. If it does, set the visibility of that button to true
        if(!flag)
            for(JButton counter: buttonList){
                if(counter.getActionCommand().contains(part)){
                    counter.setVisible(true);
                }
            }
        
        //Set the back button to true afterwards
        backButton.setVisible(true);
    }
    
    /**
     * Displays the highlight selection of buttons
     * @param b state of the buttons visibility
     */
    public void showHighlights(boolean b)
    {
        highlighter.setVisible(b);
        stopH.setVisible(!b);
    }
    
    /**
     * Displays the conformation selection of buttons
     * @param b state of the buttons visibility
     */
    public void showConfirmation(boolean b)
    {
        yes.setVisible(b);
        no.setVisible(b);
        confirm.setVisible(b);
    }
    
    /**
     * Hides all of the buttons
     */
    public void hideAll()
    {
        //Calls each show method and put false in parameter to hide them
        this.showGates(false);
        this.showTransistors(false);
        this.showCapacitors(false);
        this.showResistors(false);
        this.showSwitches(false);
        this.showDiodes(false);
        this.showPowerSupplies(false);
        this.showICS(false);
        this.showWire(false);
        this.showMain(false);
        this.showStop(false);
        //These buttons are not in any method so just set them to invisible
        this.easterEgg.setVisible(false);
        this.inputPanel.setVisible(false);
        this.backButton.setVisible(false);      
    }
    
    /**
     * Sets the action command for all of the buttons. This is used for getting the
     * action command in controller classes.
     */
    public void setActionCommandsAllButtons()
    {
        gates.setActionCommand("gates");
        ics.setActionCommand("ics");
        capacitors.setActionCommand("capacitors");
        switches.setActionCommand("switches");
        resistors.setActionCommand("resistors");
        transistors.setActionCommand("transistors");
        wire.setActionCommand("wire");
        powerSupplies.setActionCommand("power supplies");
        diodes.setActionCommand("diodes");
        andGate.setActionCommand("and gate");
        orGate.setActionCommand("or gate");
        nandGate.setActionCommand("nand gate");
        norGate.setActionCommand("nor gate");
        notGate.setActionCommand("not gate");
        xorGate.setActionCommand("xor gate");
        flipFlop.setActionCommand("flip flop");
        npn.setActionCommand("npn transistor");
        pnp.setActionCommand("pnp transistor");
        oneMF.setActionCommand("one mf capacitor");
        tenMF.setActionCommand("ten mf capacitor");
        oneHundredMF.setActionCommand("onehundred mf capacitor");
        oneKRes.setActionCommand("one k resistor");
        tenKRes.setActionCommand("ten k resistor");
        fourSeventyRes.setActionCommand("fourseventy resistor");
        plusBattery.setActionCommand("positive battery");
        ground.setActionCommand("ground");
        greenLED.setActionCommand("green led");
        redLED.setActionCommand("red led");
        yellowLED.setActionCommand("yellow led");
        blueLED.setActionCommand("blue led");
        diode.setActionCommand("diode");
        tradSwitch.setActionCommand("traditional switch");
        plusSwitch.setActionCommand("positive switch");
        negSwitch.setActionCommand("negative switch");
        hBridge.setActionCommand("hbridge");
        pic24.setActionCommand("pic24");
        blackWire.setActionCommand("black wire");
        blueWire.setActionCommand("blue wire");
        yellowWire.setActionCommand("yellow wire");
        greenWire.setActionCommand("green wire");
        orangeWire.setActionCommand("orange wire");
        purpleWire.setActionCommand("purple wire");
        redWire.setActionCommand("red wire");
        createCapacitor.setActionCommand("create custom capacitor");
        createResistor.setActionCommand("create custom resistor");
        createBattery.setActionCommand("create custom battery");
        deleteComponent.setActionCommand("deletecomponent");
        backButton.setActionCommand("back");
        startStopSimulate.setActionCommand("simulate");
        
        easterEgg.setActionCommand("easter egg"); //...what??
    }
    
    /**
     * Adds all the buttons to Arraylist buttonList
     */
    public void addButtonsToList()
    {    
        //These buttons are added in a specific order because they will
        //be shown from left to right depending on when they are added
        buttonList.add(this.andGate);
        buttonList.add(this.orGate);
        buttonList.add(this.nandGate);
        buttonList.add(this.norGate);
        buttonList.add(this.notGate);
        buttonList.add(this.xorGate);
        buttonList.add(this.flipFlop);
       
        buttonList.add(this.npn);
        buttonList.add(this.pnp);
        
        buttonList.add(this.oneMF);
        buttonList.add(this.tenMF);
        buttonList.add(this.oneHundredMF);
        
        buttonList.add(this.oneKRes);
        buttonList.add(this.tenKRes);
        buttonList.add(this.fourSeventyRes);

        buttonList.add(this.plusBattery);
        buttonList.add(this.ground);
        
        buttonList.add(this.greenLED);
        buttonList.add(this.redLED);
        buttonList.add(this.yellowLED);
        buttonList.add(this.blueLED);
        buttonList.add(this.diode);
        
        buttonList.add(this.tradSwitch);
        buttonList.add(this.plusSwitch);
        buttonList.add(this.negSwitch);
        
        buttonList.add(this.hBridge);
        buttonList.add(this.pic24);
        
        buttonList.add(this.blackWire);
        buttonList.add(this.blueWire);
        buttonList.add(this.yellowWire);
        buttonList.add(this.greenWire);
        buttonList.add(this.orangeWire);
        buttonList.add(this.purpleWire);
        buttonList.add(this.redWire);    
        
        buttonList.add(this.createResistor);
        buttonList.add(this.createCapacitor);
        buttonList.add(this.createBattery);
        buttonList.add(this.stop);
        buttonList.add(this.deleteComponent);
        buttonList.add(this.backButton);


    }
    
    /**Accessor method to get the schematicPanel. Used for the draw wire in
     * mousecontroller2.
     * @return the schematicPanel
     */ 
    public JPanel getSchematicPanel()
    {
        return schematicPanel;
    }
    
    /**Creates a new drawing in the list of drawings, and places it at the top
     * left corner of the screen
     * @param parent    The part name
     * @param num       Any extra value that the part needs (Ex. resistor resistance)
     */ 
    public void createPart(String parent, int num)
    {
        //If the parent equals any of these names, add a new drawing to the list
        //and inside call the model create part with the parent and a num. The model
        //will return a name for the drawing
        if (parent.equals("andgate"))         
            list.add(new Drawing("andgate",model.createPart(parent, num),num));    
        else if (parent.equals("orgate"))
            list.add(new Drawing("orgate",model.createPart(parent, num),num));    
        else if (parent.equals("norgate"))
            list.add(new Drawing("norgate",model.createPart(parent, num),num));    
        else if (parent.equals("xorgate"))
            list.add(new Drawing("xorgate",model.createPart(parent, num),num));    
        else if (parent.equals("nandgate"))
            list.add(new Drawing("nandgate",model.createPart(parent, num),num));    
        else if (parent.equals("notgate"))
            list.add(new Drawing("notgate",model.createPart(parent, num),num));    
        else if (parent.equals("flipflop"))
            list.add(new Drawing("flipflop",model.createPart(parent, num),num));    
        else if (parent.equals("resistor"))
            list.add(new Drawing("resistor",model.createPart(parent, num),num));    
        else if (parent.equals("capacitor"))
            list.add(new Drawing("capacitor",model.createPart(parent, num),num));    
        else if (parent.equals("plusbattery"))
            list.add(new Drawing("plusbattery",model.createPart(parent, num),num));    
        else if (parent.equals("negbattery"))
            list.add(new Drawing("negbattery",model.createPart(parent, num),num));    
        else if (parent.equals("transistor"))
            list.add(new Drawing("transistor",model.createPart(parent, num),num));    
        else if (parent.equals("switch"))
            list.add(new Drawing("switch",model.createPart(parent, num),num));    
        else if (parent.equals("led"))
            list.add(new Drawing("led",model.createPart(parent, num),num));    
        else if (parent.equals("diode"))
            list.add(new Drawing("diode",model.createPart(parent, num),num));  
        else if (parent.equals("hbridge"))
        {
            list.add(new Drawing("hbridge",model.createPart(parent, num),num));
            //Increase hbridge amount
            numHBridge++;
        }
        else if (parent.equals("pic24"))
        {
            list.add(new Drawing("pic24",model.createPart(parent, num),num));
            //increas pic amount
            numPic++;
        }
        
        //Add the drawing to the schematicpanel
        schematicPanel.add(list.get(list.size()-1));
        list.get(list.size()-1).addMouseListener(mouse2);
        list.get(list.size()-1).addMouseMotionListener(mouse1);
        
        //If the part is an hbridge or pic, set the special bounds because they
        //are bigger than all the other pictures
        if (parent.equals("hbridge"))
            list.get(list.size()-1).setBounds(50,50,120,30);
        else if (parent.equals("pic24"))
            list.get(list.size()-1).setBounds(50,50,240,30);
        //If it is another picture, then set the bounds to this
        else
            list.get(list.size()-1).setBounds(50,50,60,30);
    }
    
    /**Creates a new wire for the schematic panel
     * @param x     The x coordinate of the first point
     * @param y     The y coordinate of the first point
     * @param c     The colour of the wire
     */ 
    public void createWire(int x, int y, int c)
    {
        //If there is a deletedIncrement not used, create a new wire with this 
        //deleted incremented name and remove it from the list of deletedincrements
        if (deletedIncrement.size()>0)
        {
            list3.add(new Wire(x, y, c, "wire" + deletedIncrement.get(0), false, 0, 0));
            deletedIncrement.remove(0);
        }
        
        //Otherwise, create a new wire with the wireIncrement name and increase the increment
        //by 1 afterwards
        else
            list3.add(new Wire(x, y, c, "wire" + wireIncrement++, false, 0, 0));
        
        //Add the wire to the schematicpanel and set its bounds to the x and y
        //coordinate subtracted by 10 to make it appear right where the mouse cursor is
        //The width and height is 20 by 20 because the wire is currently a crosshair
        schematicPanel.add(list3.get(list3.size()-1));
        list3.get(list3.size()-1).setBounds(x-10,y-10,20,20);
    }
    
    /**Creates a new wire right on the spot skipping the crosshair part
     * @param x     The x coordinate of the first point
     * @param y     The y coordinate of the first point
     * @param c     The colour of the wire
     * @param x2    The x coordinate of the second point
     * @param y2    The y coordinate of the second point
     */ 
    public void createWire(int x, int y, int c, int x2, int y2)
    {
        //Refer to first create wire
        if (deletedIncrement.size()>0)
        {
            //set the last 3 parameters to true and the x2 and y2 so the wire object will draw the wire right away, skipping the crosshair
            list3.add(new Wire(x+3, y+3, c, "wire" + deletedIncrement.get(0), true, x2-6, y2-6));  
            deletedIncrement.remove(0);
        }
        
        else
            list3.add(new Wire(x+3, y+3, c, "wire" + wireIncrement++, true, x2-6, y2-6));    
        
        //set the bounds to the 2 points and add it to the schematic panel
        list3.get(list3.size()-1).setBounds(x, y, x2, y2);
        schematicPanel.add(list3.get(list3.size()-1));
    }
    
    /** Draws the most recent wire out so that it will actually appear as a wire instead
     * of a crosshair
     * @param ex     The x coordinate of the second point
     * @param why    The y coordinate of the second point
     */ 
    public void drawWire(int ex, int why)
    {
        
        Wire temp = list3.get(list3.size()-1);
        //Set the x and y to the second point x and y subtract the first point
        int x = (int)(ex-temp.getFirstPoint().x);
        int y = (int)(why-temp.getFirstPoint().y);
        
        //Need to find the top left point to draw the wire correctly
        Point p; //create a new point
        //if x is greater than y, then the point will be the second point x and the first point y
        if (Math.abs(x) > Math.abs(y))
            p = new Point(ex,(int)temp.getFirstPoint().y);
        else
            //else, the first point will be the first point x and the second point y
            p = new Point((int)temp.getFirstPoint().x, why);

        //make x equal to the new point x minus the first point of x
        //and make y equal to the new point y minus the first point of y
        x = (int)(p.x-temp.getFirstPoint().x);
        y = (int)(p.y-temp.getFirstPoint().y);

        //If x or y is less than 0, then the startPoint is the point created. Otherwise,
        //the startPoint is the first point
        Point startPoint;
        if (x<0 || y<0)
            startPoint = p;
        else
            startPoint = temp.getFirstPoint();   
        
        //Call the wire's setThisBounds and set it to the new point (x,y). Set the second point
        //to p, and call the repaint to draw the line. Set the bounds to fit the line
        temp.setThisBounds(new Point(x,y));
        temp.setSecondPoint(p);
        temp.repaint();
        temp.setBounds(startPoint.x-3,startPoint.y-3,Math.abs(x)+6,Math.abs(y)+6);
    }  
    
    /** Moves a drawing into a new location
     * @param d     The drawing that will be moved
     * @param px    How much to the right/left the drawing will be moved to
     * @param py    how much to the up/down the drawing will be moved to
     */ 
    public void dragDrop(Drawing d, int px, int py)
    {
        if (d != null)
        {
            int x, y;
            //the x and y coordinate of the start bound will be the top left point
            //of the drawings location plus px and py. Also round it to the nearest
            //5 pixels to snap it
            x = (int) Math.round((d.getBounds().x+px)/5.0)*5;
            y = (int) Math.round((d.getBounds().y+py)/5.0)*5;
            
            //If the orientation of the drawing is horizontal, set the width and height
            // to 60, 30. If its an hbridge or a pic, set its
            //special bounds. If the orientation is vertical, set the width and height to 30, 60
            //or if its an ic, set its special bounds
            if (d.getOrientation()%2 == 0)
                if (d.getPart().equals("hbridge"))
                    d.setBounds(x,y,120,30);
                else if (d.getPart().equals("pic24"))
                    d.setBounds(x,y,240,30);
                else
                    d.setBounds(x,y,60,30);
            else
                if (d.getPart().equals("hbridge"))
                    d.setBounds(x,y,30,120);
                else if (d.getPart().equals("pic24"))
                    d.setBounds(x,y,30,240);
                else
                    d.setBounds(x,y,30,60);
        }
    }
    
    /**Sets the startStopSimulate button text to the new one and the actioncommand as well
     * @param n     The new name of the button
     * @param s     The new action command of the button
     */ 
    public void setStartStopSimulate (String n, String s)
    {
        startStopSimulate.setText(n);
        startStopSimulate.setActionCommand(s);
    }
    
    /**Shows the stop button
     * @param b state of the button's visibility
     */ 
    public void showStop(boolean b)
    {
        stop.setVisible(b);
    }
    
    /**Removes a drawing/part from the schematic
     * @param d      The drawing that will be removed
     */ 
    public void removePart(Drawing d)
    {
        //Remove it from the schematicpanel
        schematicPanel.remove(d);
        schematicPanel.repaint();
        
        //For each of the wires, if the wire has a connection to d, remove it.
        for (Wire w: list3)
        {
            if (w.getPartConnections().contains(d))
            {
                w.getPartSides().remove(w.getPartConnections().indexOf(d));
                w.getPartConnections().remove(d);
            }
        }
        
        //If the part is a pic or hbridge, decrease the number of parts the schematic has
        if (d.getParent().equals("pic24"))
            numPic--;
        else if (d.getParent().equals("hbridge"))
            numHBridge--;
        
        //removes d from the list of drawings
        list.remove(d);      
    }
    
    /**Removes the most recent wire from the schematic
     */ 
    public void removeWire()
    {
        //Adds the deleted incremented number that will be used when a new wire is created
        deletedIncrement.add(Integer.parseInt(list3.get(list3.size()-1).getName().substring(4)));
        //Remove the wire from the schematic panel and repaint it
        schematicPanel.remove(list3.get(list3.size()-1));
        schematicPanel.repaint();
        //Remove the wire from the list
        list3.remove(list3.get(list3.size()-1)); 
    }
    
    /**Remove a wire from the schematic
     * @param w     The wire that will be removed
     */ 
    public void removeWire(Wire w)
    {
        //Adds the deleted incremented number that will be used when a new wire is created
        deletedIncrement.add(Integer.parseInt(w.getName().substring(4)));
        //Remove the wire from the schematic panel and repaint it
        schematicPanel.remove(w);
        schematicPanel.repaint();
        //remove the wire from the list
        list3.remove(w);
    }
    
    /** Sets all of the button icons.
     */ 
    public void setButtonIcons()
    {
        andGate.setIcon(new ImageIcon ("Resorces/ANDGate (right).png"));
        orGate.setIcon(new ImageIcon ("Resorces/ORGate (right).png"));
        nandGate.setIcon(new ImageIcon ("Resorces/NANDGate (right).png"));
        norGate.setIcon(new ImageIcon ("Resorces/NORGate (right).png"));
        notGate.setIcon(new ImageIcon ("Resorces/NOTGate (right).png"));
        xorGate.setIcon(new ImageIcon ("Resorces/XORGate (right).png"));
        flipFlop.setIcon(new ImageIcon ("Resorces/flipflop (right).png"));
        npn.setIcon(new ImageIcon ("Resorces/TransistorNPN (right).png"));
        pnp.setIcon(new ImageIcon ("Resorces/TransistorPNP (right).png"));
        oneMF.setIcon(new ImageIcon ("Resorces/Capacitor (right).png"));
        tenMF.setIcon(new ImageIcon ("Resorces/Capacitor (right).png"));
        oneHundredMF.setIcon(new ImageIcon ("Resorces/Capacitor (right).png"));
        oneKRes.setIcon(new ImageIcon ("Resorces/Resistor (right).png"));
        tenKRes.setIcon(new ImageIcon ("Resorces/Resistor (right).png"));
        fourSeventyRes.setIcon(new ImageIcon ("Resorces/Resistor (right).png"));
        plusBattery.setIcon(new ImageIcon ("Resorces/BatteryPOS (right).png"));
        ground.setIcon(new ImageIcon ("Resorces/BatteryNEG (right).png"));
        greenLED.setIcon(new ImageIcon ("Resorces/LEDGreen (off)(right).png"));
        redLED.setIcon(new ImageIcon ("Resorces/LEDRed (off)(right).png"));
        yellowLED.setIcon(new ImageIcon ("Resorces/LEDYellow (off)(right).png"));
        blueLED.setIcon(new ImageIcon ("Resorces/LEDBlue (off)(right).png"));
        diode.setIcon(new ImageIcon ("Resorces/Diode (right).png"));
        tradSwitch.setIcon(new ImageIcon ("Resorces/TRADSwitchNEG (right).png"));
        plusSwitch.setIcon(new ImageIcon ("Resorces/SwitchPOS (right).png"));
        negSwitch.setIcon(new ImageIcon ("Resorces/SwitchNEG (right).png"));
        hBridge.setIcon(new ImageIcon ("Resorces/hbridge (right).png"));
        pic24.setIcon(new ImageIcon ("Resorces/PIC24 (right).png"));
        blackWire.setIcon(new ImageIcon ("Resorces/WireBlack.png"));
        blueWire.setIcon(new ImageIcon ("Resorces/WireBlue.png"));
        yellowWire.setIcon(new ImageIcon ("Resorces/WireYellow.png"));
        greenWire.setIcon(new ImageIcon ("Resorces/WireGreen.png"));
        orangeWire.setIcon(new ImageIcon ("Resorces/WireOrange.png"));
        purpleWire.setIcon(new ImageIcon ("Resorces/WirePurple.png"));
        redWire.setIcon(new ImageIcon ("Resorces/WireRed.png"));
        easterEgg.setIcon(new ImageIcon("Resorces/DarkVoice.png"));
    }
    
    /** Updates the bounds of a drawing after it is rotated.
     * @param d     The drawing that has just been rotated
     */ 
    public void updateBounds(Drawing d)
    {
        //If the drawing is now horizontal, set its new bounds
        if (d.getOrientation()%2 == 0)
        {
            if (d.getPart().equals("hbridge"))
                d.setBounds(d.getBounds().x-45,d.getBounds().y+45,120,30);
            else if (d.getPart().equals("pic24"))
                d.setBounds(d.getBounds().x-90,d.getBounds().y+90,240,30);
            else
                d.setBounds(d.getBounds().x-15,d.getBounds().y+15,60,30);
        }
        //If the drawing is now vertical, set its new bounds
        else
        {
            if (d.getPart().equals("hbridge"))
                d.setBounds(d.getBounds().x+45,d.getBounds().y-45,30,120);
            else if (d.getPart().equals("pic24"))
                d.setBounds(d.getBounds().x+90,d.getBounds().y-90,30,240);
            else
                d.setBounds(d.getBounds().x+15,d.getBounds().y-15,30,60);
        }
    }
    
    /**Finds all of the connections this drawing should be connected to
     * @param d     the drawing that the method is working on
     * @param w     a wire that the drawing should not be connected to
     */ 
    public void findConnection(Drawing d, Wire wire)
    {
         //If there is no drawing selected, go back
        if (d == null)
            return;
        
         //Flag for finding a connection
        boolean flag = false;
        //If there is a connection to a drawing, set this variable to that
        //connected side so the program runs more efficiently
        HashSet <Integer> connectedSides = new HashSet <Integer> ();
        
        //For each side of this drawing
        for (int sides=1; sides<=d.getSides(); sides++)
        {
            flag = false;
            //Look through each of the drawings in the arraylist
            for (Drawing dr: list)
            {
                //Makes sure second drawing is not the first
                if (dr.equals(d));
                
                else
                {
                    //For each of the sides of the drawing in the list
                    for (int sides2=1; sides2<=dr.getSides() && !flag; sides2++)
                        //if the the side's wire is touching this drawing's wire
                         if ((dr.getBounds().x+dr.getXOffside(sides2) == d.getBounds().x+d.getXOffside(sides)) && (dr.getBounds().y+dr.getYOffside(sides2) == d.getBounds().y+d.getYOffside(sides)))
                        {
                            //Connect the two parts
                            model.setConnection(d.getName(),sides,dr.getName(),sides2);
                            //Break all of these for loops
                            flag = true;
                            //Store the side that got connected into this
                            connectedSides.add(sides);
                        }

                    //If a connection has been set, break the loop
                    if (flag)
                        break;
                }
            }
        }
        
        //For each side of this drawing
        for (int sides=1; sides<=d.getSides(); sides++)
        {
            //If this side is the connected side, skip it because it is done
            if (connectedSides.contains(sides))
                ;
            else
                //For each of the wires in the list
                for (Wire w: list3)
                    {
                        if (w.equals(wire));
                        
                        else
                        {
                            //The x and y point of this side of the drawing
                            int x = d.getBounds().x + d.getXOffside(sides);
                            int y = d.getBounds().y + d.getYOffside(sides);

                            //If the drawing's wires are touching this wire, connect the two and all of the drawings connected to that wire
                            if (((x==w.getBounds().x+3) && (y==w.getBounds().y+3))
                              ||((x==w.getBounds().x+w.getWidth()-3) && (y==w.getBounds().y+w.getHeight()-3)))
                            {
                                w.setConnection(d, sides);
                                //go through the list of this wire's part connections and connect that part with the drawing
                                for (int counter=0; counter<w.getPartConnections().size(); counter++)
                                {
                                    Drawing dr = w.getPartConnections().get(counter);
                                    int sides2 = w.getPartSides().get(counter);
                                    model.setConnection(d.getName(),sides, dr.getName(), sides2);                            
                                }
                                //Set this wire to be checked
                                w.setChecked(true);
                                //Call the addWiredParts to go through all other wire connections to this wire to see if they are connected
                                //to parts as well that the drawing should be connected to
                                addWiredParts(d, sides, w);
                                //resets all the checked of the wire
                                resetChecked(w);
                                break;
                            }
                        }
                    }
        }
    }
    
    /**Finds all of the connections that should be connected to a wire
     * @param w     The wire that this method will be working with
     */ 
    public void findConnection(Wire w)
    {
        boolean flag = false;
        
        //The actual x and y coordinates of where the wire starts
        int x = w.getBounds().x+3;
        int y = w.getBounds().y+3;
        
        //Do this twice because there are two points to look for connections of the wire.
        for (int counter=0; counter<2; counter++)
        {
            //Look through each of the drawings in the arraylist
            for (Drawing d: list)
            {
                //For each of the drawing's sides, if the side's edge coordinates is equal
                //to the wire's connect the two.
                for (int sides=1; sides<=d.getSides() && !flag; sides++)
                {
                    int x2 = d.getBounds().x+d.getXOffside(sides);
                    int y2 = d.getBounds().y+d.getYOffside(sides);
                    if ((x2 == x)&& (y2 == y))
                    {    
                        //refer to the above findConnection part with the wires
                        for (int count=0; count<w.getPartConnections().size(); count++)
                        {
                            Drawing dr = w.getPartConnections().get(count);
                            int sides2 = w.getPartSides().get(count);
                            model.setConnection(d.getName(),sides, dr.getName(), sides2);                            
                        }
                        w.setConnection(d, sides);
                        addWiredParts(d, sides, w);
                        resetChecked(w);
                        flag = true;
                        break;
                    }
                }
                
                //breaks the loop if flag is true
                if (flag)
                    break;
            }
            
            //If no connection has been found so far look through the wires list
            if (!flag)
                for (Wire w2: list3)
                {
                    //If the wire is itself, do nothing
                    if (w2.equals(w));
                    
                    else
                        //if the wire is in the bounds of the second wire, connect the 2
                        if ((x>=w2.getBounds().x+3) && (x<=w2.getBounds().x+w2.getWidth()-3 ) && (y>=w2.getBounds().y+3) && (y<=w2.getBounds().y+w2.getHeight()-3))
                        {
                            w.setConnection(w2);
                            w2.setConnection(w);
                            //Call the make connections so all the drawings will connect
                            makeConnections(w);
                            //reset the checked3 of wires
                            resetChecked3();
                        }
                }
            
            //loop this again with these new coordinates (the second point of the wire)
            x = w.getBounds().x+w.getWidth()-3;
            y = w.getBounds().y+w.getHeight()-3;
            flag = false;
        }
    }
    
    public void findConnection()
    {
        findConnection(list3.get(list3.size()-1));
    }
    
    /** Connects all the drawings together after a wire has been connected with another wire
     * @param   w       the wire this method is working with
     */ 
    public void makeConnections(Wire w)
    {
        //If the wire has a drawing connection
        if (w.getPartConnections().size() > 0)
        {
            //For all of the drawings connected to this wire, call the addWiredParts
            //with the drawing, side and this wire. Reset checked everytime addWiredParts
            //is called
            for (int count=0; count<w.getPartConnections().size(); count++)
            {
                Drawing dr = w.getPartConnections().get(count);
                int sides2 = w.getPartSides().get(count);
                w.setChecked(true);
                addWiredParts(dr, sides2, w);
                resetChecked(w);
            }
        }
        
        else
        {
            //Go through the list of wire connections
            for (Wire w2: w.getWireConnections())
            {
                //if it is already checked, do nothing
                if (w2.isChecked3());
                
                //same as above, if there is a part connected to this wire
                else if (w2.getPartConnections().size() > 0)
                {
                    w2.setChecked3(true);
                    for (int count=0; count<w2.getPartConnections().size(); count++)
                    {
                        Drawing dr = w2.getPartConnections().get(count);
                        int sides2 = w2.getPartSides().get(count);
                        w2.setChecked(true);
                        addWiredParts(dr, sides2, w2);
                        resetChecked(w);
                    }
                }
                
                //If there still isnt, set this wire's checked3 to true and
                //call this method again with w2 as the parameter
                else
                {
                    w2.setChecked3(true);
                    makeConnections(w2);
                }
            }
        }
    }
    
    /** Adds all of the wire connections to the drawing
     * @param d     The drawing that will be added to the drawings connected to the wires
     * @param sides The side of the drawing
     * @param w     The wire that has the connection
     */ 
    public void addWiredParts(Drawing d, int sides, Wire w)
    {
        //go through each of the wire's wire connections
        for (Wire w2: w.getWireConnections())
        {
            //if the wire is checked then do nothing
            if (w2.isChecked());
            
            else
            {
                //go through each of the second wire's part connections and connect the part to the drawing
                for (int counter=0; counter<w2.getPartConnections().size(); counter++)
                {
                    Drawing dr = w2.getPartConnections().get(counter);
                    int sides2 = w2.getPartSides().get(counter);
                    model.setConnection(d.getName(),sides, dr.getName(), sides2);                            
                }
                //set this wire to be checked
                w2.setChecked(true);
                //call this method with the same drawing but using the second wire
                addWiredParts(d, sides, w2);              
            }
        }
    }
    
    /**Resets all of the wire's checked that were changed to true
     * @param w     The starting wire that got checked
     */ 
    public void resetChecked(Wire w)
    {
        //Go through each of the wire connections and if the wire is checked,
        //set the checked to false, then call this method again using that wire
        //as the parameter
        for (Wire w2: w.getWireConnections())
        {
            if (w2.isChecked())
            {
                w2.setChecked(false);
                resetChecked(w2);
            }         
        }
    }
    
    /** Resets all of the wire's checked2
     */ 
    public void resetChecked()
    {
        //Goes through each of the wires in the list and sets the checked2 to false
        for (Wire w: list3)
            w.setChecked2(false);
    }
    
    /** Resets all of the wire's checked3
     */ 
    public void resetChecked3()
    {
        //Goes through each of the wires in the list and sets the checked3 to false
        for (Wire w: list3)
            w.setChecked3(false);
    }
    
    /** Removes all of the connections to a drawing
     * @param d     The drawing
     * @param w     The one wire that will not remove drawing
     */ 
    public void removeConnections(Drawing d, Wire w)
    {
        for (Wire w2: list3)
        {
            //If the wire equals the one in the parameter, do nothing
            if (w2.equals(w));
            
            else
                w2.removeConnections(d);
        }
    }
    
    /**Remove all of the connections to this wire
     * @param w     This wire
     */ 
    public void removeConnections(Wire w)
    {     
        //While the wire's part connection size is greater than 0, remove all
        //the drawing connections that are connected to this wire. Then, find the drawing's
        //new connection using this wire as the excluded
        while (w.getPartConnections().size()>0)
        {
            Drawing d = w.getPartConnections().get(0);
            model.removeAllConnections(d.getName());
            removeConnections(d, null);
            findConnection(d, w);
        } 
        
        //for each wire, remove this wire connection and call restartConnections
        //using the new wire as the parameter
        for (Wire w2: w.getWireConnections())
        {
            if (w2.equals(w));
            
            else
            {
                w2.removeConnections(w);
                restartConnections(w2);  
            }
        }
        
        //Remove all connections to this wire
        w.removeAllConnections(); 
        //Resets the checked
        resetChecked();
    }
    
    /**Restarts the connections of the wire in the parameter
     * @param w     The wire that will get a restarted connection
     */ 
    public void restartConnections(Wire w)
    {
        //For each of the drawings that are connected to this wire, restart the
        //drawing's connections by removing it and finding a new connection
        for (Drawing d: w.getPartConnections())
        {
            model.removeAllConnections(d.getName());
            removeConnections(d, w);
            findConnection(d, w);
        }
        
        //Set the checked2 to true
        w.setChecked2(true);
        
        //Go through each of the wire's wire connections and if the wire is checked(2)
        //do nothing. Else, call the restartConnections with the new wire as the parameter)
        for (Wire w2: w.getWireConnections())
        {
            if (w2.isChecked2());
            
            else
                restartConnections(w2);
        }
    }
    
    /** Changes the labels of switches to buttons.
     */
    public void changeLabelOn()
    {
        int x = 0,  //the x coordinate of the top left corner of the switch
            y = 0;  //the y coordinate of the top left corner of the switch
        
        //for each drawing on the list
        for (Drawing check : list)
        {
            //String a = check.getPart();
            //If the part equals switch create a new JButton and set the correct picture
            if (check.getPart().equals("switch"))
            {           
                if(check.getValue() == Drawing.TRAD_SWITCH)
                {
                    x = check.getBounds().x;
                    y = check.getBounds().y;
                    //turns the drawing off
                    check.setVisible(false);
                    //create the new button and add an action listener to it
                    tradSwitchButton = new JButton();
                    tradSwitchButton.addActionListener(changeController);
                    tradSwitchButton.setActionCommand("trad switch");
                    
                    //If the drawing is horizontal, set the width and height to 60,30
                    //and set the icon to a horizontal off traditional switch 
                    if (check.getOrientation()%2 == 0)
                    {
                        tradSwitchButton.setBounds(x,y,60,30);
                        tradSwitchButton.setIcon(new ImageIcon ("Resorces/TRADSwitchNEG (right).png"));
                        tradSwitchButton.setToolTipText("Switch (off)");
                    }
                    
                    //if the drawing is vertical, set the width and height to 30, 60
                    //and set the icon to a vertical off traditional switch
                    else
                    {
                        tradSwitchButton.setBounds(x,y,30,60);
                        tradSwitchButton.setIcon(new ImageIcon ("Resorces/TRADSwitchNEG (down).png"));
                        tradSwitchButton.setToolTipText("Switch (off)");
                    }                    
                    
                    //add the button to the arraylist of switch buttons, add it
                    //to the schematic panel, and set the drawing's button to be
                    //this one
                    switchButtonList.add(tradSwitchButton);
                    schematicPanel.add(tradSwitchButton);
                    tradSwitchButton.setVisible(true);
                    check.setButton(tradSwitchButton);
                }
                
                else if (check.getValue() == Drawing.PLUS_SWITCH)
                {
                    //same as the traditional switch but set the icons to positive
                    //switches
                    x = check.getBounds().x;
                    y = check.getBounds().y;
                    check.setVisible(false);
                    posSwitchButton = new JButton();
                    posSwitchButton.addActionListener(changeController);
                    posSwitchButton.setActionCommand("pos switch");
                    
                    if (check.getOrientation() == 0)
                    {   
                        posSwitchButton.setBounds(x,y,60,30);
                        posSwitchButton.setIcon(new ImageIcon ("Resorces/SwitchPOS (right).png"));
                        posSwitchButton.setToolTipText("Positive Switch (off)");
                    }
                    else if (check.getOrientation() == 1)
                    {
                        posSwitchButton.setBounds(x,y,30,60);
                        posSwitchButton.setIcon(new ImageIcon ("Resorces/SwitchPOS (down).png"));
                        posSwitchButton.setToolTipText("Positive Switch (off)");
                    }
                    else if (check.getOrientation() == 2)
                    {
                        posSwitchButton.setBounds(x,y,60,30);
                        posSwitchButton.setIcon(new ImageIcon("Resorces/SwitchPOS (left).png"));
                        posSwitchButton.setToolTipText("Positive Switch (off)");
                    }
                    else
                    {
                        posSwitchButton.setBounds(x,y,30,60);
                        posSwitchButton.setIcon(new ImageIcon("Resorces/SwitchPOS (up).png"));
                        posSwitchButton.setToolTipText("Positive Switch (off)");
                    }
                    
                    //set the border to be red to represent that the switch is off
                    posSwitchButton.setBorder(BorderFactory.createLineBorder(Color.RED));
                    switchButtonList.add(posSwitchButton);
                    schematicPanel.add(posSwitchButton);
                    posSwitchButton.setVisible(true);
                    check.setButton(posSwitchButton);
                }
                
                else
                {
                    //same as the other two, but set the icons to negative swiches
                    x = check.getBounds().x;
                    y = check.getBounds().y;
                    check.setVisible(false);
                    negSwitchButton = new JButton();
                    negSwitchButton.addActionListener(changeController);
                    negSwitchButton.setActionCommand("neg switch");
                    
                    if (check.getOrientation() == 0)
                    {
                        negSwitchButton.setBounds(x,y,60,30);
                        negSwitchButton.setIcon(new ImageIcon ("Resorces/SwitchNEG (right).png"));
                        negSwitchButton.setToolTipText("Negative Switch (off)");
                    }
                    else if (check.getOrientation() == 1)
                    {
                        negSwitchButton.setBounds(x,y,30,60);
                        negSwitchButton.setIcon(new ImageIcon ("Resorces/SwitchNEG (down).png"));
                        negSwitchButton.setToolTipText("Negative Switch (off)");
                    }
                    else if (check.getOrientation() == 2)
                    {
                        negSwitchButton.setBounds(x,y,60,30);
                        negSwitchButton.setIcon (new ImageIcon ("Resorces/SwitchNEG (left).png"));
                        negSwitchButton.setToolTipText("Negative Switch (off)");
                    }
                    else
                    {
                        negSwitchButton.setBounds(x,y,30,60);
                        negSwitchButton.setIcon(new ImageIcon ("Resorces/SwitchNEG (up).png"));
                        negSwitchButton.setToolTipText("Negative Switch (off)");
                    }    
                    
                    //set a red border around the button to show that it is off
                    negSwitchButton.setBorder(BorderFactory.createLineBorder(Color.RED));
                    switchButtonList.add(negSwitchButton);
                    schematicPanel.add(negSwitchButton);
                    negSwitchButton.setVisible(true);
                    check.setButton(negSwitchButton);
                }
            }
        }
    }
    
    /** Changes the switch buttons back to labels.
     */
    public void changeLabelOff()
    {
        //if one of the switch buttons is visible
        if (tradSwitchButton.isVisible() == true || posSwitchButton.isVisible() == true || negSwitchButton.isVisible() == true)
        {
            //for each of the drawings on the list, if the drawing is a switch,
            //reset the button and set the visibility of it to be true
            for (Drawing check : list)
            {
                if (check.getPart().equals("switch"))
                {
                    if (check.getValue() == Drawing.TRAD_SWITCH)
                    {
                        check.setVisible(true);
                        check.setButton(null);
                    }
                    else if (check.getValue() == Drawing.PLUS_SWITCH)
                    {
                        check.setVisible(true);
                        check.setButton(null);
                    }
                    else if (check.getValue() == Drawing.NEG_SWITCH)
                    {
                        check.setVisible(true);
                        check.setButton(null);
                    }  
                }
            }
            
            //remove the buttons from the panel
            for (JButton list : switchButtonList)
            {
                if (list.getActionCommand().equals("trad switch"))
                    schematicPanel.remove(list);
                else if (list.getActionCommand().equals("pos switch"))
                    schematicPanel.remove(list);
                else if (list.getActionCommand().equals("neg switch"))
                    schematicPanel.remove(list);
            }
            
            //clear the list
            switchButtonList.clear();
            
            //For each drawing, if the part is a switch, change the switch of that
            //part to off
            for (Drawing d: list)
            {
                if (d.getParent().equals("switch"))
                    model.changeSwitch(d.getName(), false);
            }
        }
    }
    
    /** Changes the switch from on to off/positive to negative.
     * @param a The name of the part
     * @param button The button that was pressed
     */
    public void changeLabel(String a, JButton button)
    {   
        if (a == "trad switch")
        {
            //if the button is a traditional switch and is horizontal, turn it off or on
            if (button.getSize().equals(new Dimension(60,30)))
            {
                if (button.getToolTipText().equals("Switch (off)"))
                {
                    //change the icon to a connected switch, update the tooltip icon,
                    //and look through the list of drawings and if the drawing's button
                    //equals this one, call the model to change the switch using this drawing's
                    //name as the parameter
                    button.setIcon(new ImageIcon ("Resorces/TRADSwitchPOS (right).png"));
                    button.setToolTipText("Switch (on)");
                    for (Drawing d: list)
                        if (button.equals(d.getButton()))
                        {
                            model.changeSwitch(d.getName(), true);
                            update();
                            break;
                        }
                }
                else if (button.getToolTipText().equals("Switch (on)"))
                {
                    //if the button is on, change it to an off switch using the same steps
                    //as changing it on, except when calling the model's changeswitch,
                    //change the boolean parameter to false for off
                    button.setIcon(new ImageIcon ("Resorces/TRADSwitchNEG (right).png"));
                    button.setToolTipText("Switch (off)");
                    for (Drawing d: list)
                        if (button.equals(d.getButton()))
                        {
                            model.changeSwitch(d.getName(), false);
                            update();
                            break;
                        }
                }
            }
            
            //else if the button horizontal, do the same except change the icon
            //to a down facing button
            else if (button.getSize().equals(new Dimension(30,60)))
            {
                if (button.getToolTipText().equals("Switch (off)"))
                {
                    button.setIcon(new ImageIcon ("Resorces/TRADSwitchPOS (down).png"));
                    button.setToolTipText("Switch (on)");
                    for (Drawing d: list)
                        if (button.equals(d.getButton()))
                        {
                            model.changeSwitch(d.getName(), true);
                            update();
                            break;
                        }
                }
                else if (button.getToolTipText().equals("Switch (on)"))
                {
                    button.setIcon(new ImageIcon ("Resorces/TRADSwitchNEG (down).png"));
                    button.setToolTipText("Switch (off)");
                    for (Drawing d: list)
                        if (button.equals(d.getButton()))
                        {
                            model.changeSwitch(d.getName(), false);
                            update();
                            break;
                        }
                }
            }
        }
        
        //If the button is a positive switch, do the same thing as the traditional switch
        //but do not change the icon. Instead, set the border to green or red based on
        //if it's on or off
        else if (a == "pos switch")
        {
            if (button.getToolTipText().equals("Positive Switch (off)"))
            {
                button.setToolTipText("Positive Switch (on)");
                button.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                for (Drawing d: list)
                    if (button.equals(d.getButton()))
                    {
                        model.changeSwitch(d.getName(), true);
                        update();
                        break;
                    }
            }
            else
            {
                button.setToolTipText("Positive Switch (off)");
                button.setBorder(BorderFactory.createLineBorder(Color.RED));
                for (Drawing d: list)
                    if (button.equals(d.getButton()))
                    {
                        model.changeSwitch(d.getName(), false);
                        update();
                        break;
                    }
            }
        }
        
        //if it is a negative switch, do the same thing as the positive switch.
        else if (a == "neg switch")
        {
            if (button.getToolTipText().equals("Negative Switch (off)"))
            {
                button.setToolTipText("Negative Switch (on)");
                button.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                for (Drawing d: list)
                    if (button.equals(d.getButton()))
                    {
                        model.changeSwitch(d.getName(), true);
                        update();
                        break;
                    }
            }
            else
            {
                button.setToolTipText("Negative Switch (off)");
                button.setBorder(BorderFactory.createLineBorder(Color.RED));
                for (Drawing d: list)
                    if (button.equals(d.getButton()))
                    {
                        model.changeSwitch(d.getName(), false);
                        update();
                        break;
                    }
            }
        }
    }
    
    /**Accessor method to get the arraylist of drawings
     * @return the arraylist of drawings
     */ 
    public ArrayList <Drawing> getListD()
    {
        return list;
    }
    
    /**Acceessor method to get the arraylist of wires
     * @return the arraylist of wires
     */ 
    public ArrayList <Wire> getListW()
    {
        return list3;
    }
    
    /** Sets the input of the input textfield to in
     * @param in    The new text the input will have
     */ 
    public void setInput(String in)
    {
        input.setText(in);
    }
    
    /** Updates all of the LEDs to turn them on or off
     */ 
    public void update () 
    {    
        //go through each LED and each drawing and if the 2 names equal, if the
        //power of side 1 and the negative power of side 2 is true, and if the type%2
        //is 0 (means that the light is off), turn it on. Else, if the type%2 is 1 
        //(means that the light is on), turn it off      
        for (LED led: model.getLights())
        {
            for (Drawing d: list)
            {
                if (led.getName().equals(d.getName()))
                {
                    if (led.isPower(1) && led.isNegPower(2))                  
                        if (d.getType()%2 == 0)
                            d.change(Drawing.ON);
                        else;
                    else
                        if (d.getType()%2 == 1)
                            d.change(Drawing.OFF);
                }
            }
        }
        
        //glitchy so will be put in further patches
//        if (model.isShortCircuit())
//            warning.setVisible(true);
//        else
//            warning.setVisible(false);
    }
    
    /**Turns off all of the LEDs
     */ 
    public void turnOffLEDs()
    {
        //For each of the drawings, if it is an led and it is on, turn it off
        for (Drawing d: list)
        {
            if (d.getPart().equals("led"))
                if (d.getType()%2 == 1)
                    d.change(Drawing.OFF);
        }
    }
    
    /**Enables or disables all the buttons
     * @param b     The enabling value
     */ 
    public void enableAllButtons(boolean b)
    {
        topBarPanel.setVisible(b);
        highlighter.setVisible(b);
        rotate.setVisible(b);
        exportParts.setVisible(b);
        save.setVisible(b);
        load.setVisible(b);
        clearAll.setVisible(b);
        //This button is always false because it should be off while
        //highlighter is on
        stopH.setVisible(false);
    }
    
    /**Enables or disables all of the actions
     * @param b     The enabling value
     */ 
    public void enableAllActions(boolean b)
    {
        //If disabling all actions, change all the modes to false
        if (!b)
        {
            mouse1.deleteMode(false);
            mouse1.startWire(false, 0);
            mouse1.setHighlight(false);
            mouse1.setUnHighlight(false);
            mouse2.deleteMode(false);
            mouse2.startWire(false);
            mouse2.setHighlight(false);
            mouse2.setUnHighlight(false);
        }
               
      //  warning.setVisible(false);
        
        //Enable mouse1 or disable it depending on b
        mouse1.enable(b);
    }
    
    /** Saves the file by converting each bounds and attributes to a text file
     */ 
    public void save()
    {
        //opens save dialog
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            try{
                savePrinter = new PrintWriter(chooser.getSelectedFile());   //gives the printwriter the file
            } catch (FileNotFoundException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        
        //If the printer is not null, start the saving process
        if (savePrinter != null)
        {
            //Fill all deleted increments that are not used up so when the file is loaded,
            //it will not get random misconnections
            String[] filler = model.fill();
            //Create a new arraylist of fake drawings
            ArrayList <Drawing> fakes = new ArrayList<Drawing>();
            //While there is a name in filler, create a new drawing and add it to
            //both the fakes and the list
            while (filler[0].length()>0)
            {           
                list.add(new Drawing(filler[1], filler[0], 0));
                Drawing d = list.get(list.size()-1);
                fakes.add(d);
                schematicPanel.add(d);
                d.setBounds(7,7,7,7);  
                filler = model.fill();      
            } 

            //Do the same for wires
            ArrayList <Wire> fakes2 = new ArrayList<Wire>();
            while (deletedIncrement.size()>0)
            {
                list3.add(new Wire(1,1,1,"wire" + deletedIncrement.get(0), false, 0, 0));
                Wire w = list3.get(list3.size()-1);
                fakes2.add(w);
                schematicPanel.add(w);
                w.setBounds(7,7,7,7);
                deletedIncrement.remove(0);
            }

            //Sorts the drawing and wire names in ascending order
            sort();

            //For each drawing, print part, the name of the part, the value of any extra,
            //the orientation, and the bounds and width and height
            for (Drawing d: list)
            {
                savePrinter.println("Part");
                savePrinter.println(d.getPart());
                savePrinter.println(d.getValue());
                savePrinter.println(d.getOrientation());   
                savePrinter.println(d.getBounds().x + " " + d.getBounds().y + " " + d.getWidth() + " " + d.getHeight());           
            }

            //Go throug the list of drawings again and print the connections of sides
            //1 to 4. A No. will be printed if there are no connections to that side
            for (Drawing d: list)
            {
                savePrinter.println("Connections");
                savePrinter.println(d.getName());
                for (String n: model.getConnections(d.getName(), 1))
                    savePrinter.print(n + " ");
                savePrinter.println("Side2");
                for (String n: model.getConnections(d.getName(), 2))
                    savePrinter.print(n + " ");
                savePrinter.println("Side3");
                for (String n: model.getConnections(d.getName(), 3))
                    savePrinter.print(n + " ");
                savePrinter.println("Side4");    
                for (String n: model.getConnections(d.getName(), 4))
                    savePrinter.print(n + " ");
                savePrinter.println("\nEnd");
            }

            //Goes through each wire and prints Wire, the colour value, the bounds, width,
            //height, and a boolean value for the highlighted mode. Also go through a list of its
            //drawing connections and print the part's name plus the part's side that is connected to this wire
            for (Wire w: list3)
            {
                savePrinter.println("Wire");
                savePrinter.println(w.getColour());
                savePrinter.println(w.getBounds().x + " " + w.getBounds().y + " " + w.getWidth() + " " + w.getHeight());   
                savePrinter.println(w.isHighlighted());
                for (int counter=0; counter<w.getPartConnections().size(); counter++)
                {
                    Drawing d = w.getPartConnections().get(counter);
                    int side = w.getPartSides().get(counter);
                    savePrinter.print(d.getName() + " " + side + " ");
                }
                //print end after all the connections are printed
                savePrinter.println("\nEnd");    
            }

            //Go through the list of wires again and print connections2. Print the name
            //of the wire, and each of the wire's names that this wire is connected to
            for (Wire w: list3)
            {
                savePrinter.println("Connections2");
                savePrinter.println(w.getName());
                for (Wire w2: w.getWireConnections())
                    savePrinter.print(w2.getName() + " ");
                //print end after all the connections are printed
                savePrinter.println("\nEnd");
            }

            //close the file
            savePrinter.close();

            //go through the list of fakes and remove them from the real list
            for (Drawing d: fakes)
            {
                model.deletePart(d.getName());
                list.remove(d);
            }

            for (Wire w: fakes2)
            {
                deletedIncrement.add(Integer.parseInt(w.getName().substring(4)));
                list3.remove(w);
            }
            
            //clear the fakes after to get rid of garbage collection
            fakes.clear();
            fakes2.clear();
        }
    }
    
    /** Loads a file by getting all of the attributes and remaking the parts
     */ 
    public void load()
    {
        //Opens the chooser dialog
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            try{
                loader = new Scanner(chooser.getSelectedFile());    //gives the scanner the file
            } catch (FileNotFoundException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        
        //If there is a file in loader
        if (loader != null)
        {
            //clear the schematic
            clearAll();
            model.clearAll();
            
            //While there are still parts to add
            while(loader.hasNext())
            {
                //Store the first word into start
                String start = loader.next();
                
                //If the start equals part, create a part with the next word and the next
                //number, set the part's roation to the next int, and set the bounds
                //to the next 4 ints
                if (start.equals("Part"))
                {
                    createPart(loader.next(), loader.nextInt());
                    Drawing d = list.get(list.size()-1);
                    int rotates = loader.nextInt();
                    for (int counter=0; counter<rotates; counter++)               
                        d.rotate();
                    d.setBounds(loader.nextInt(), loader.nextInt(), loader.nextInt(), loader.nextInt());
                }

                else if (start.equals("Connections"))
                {       
                    //If the start eequals connections
                    //store the word as the name and the next word as the connection
                    String name = loader.next();
                    String connection = loader.next();

                    //If connection equals no, then skip
                    if (connection.equals("No."))
                        loader.next();

                    else
                    {
                        //Connect each connnection to the name and side 1
                        //while connection doesn't equal side2
                        do
                        {
                            model.connect(name, 1, connection);
                            connection = loader.next();
                        } while (!connection.equals("Side2"));  
                    }

                    //Repeat the same process for connections of side 2
                    connection = loader.next();

                    if (connection.equals("No."))
                        loader.next();

                    else
                    {
                        do
                        {
                            model.connect(name, 2, connection);
                            connection = loader.next();
                        } while (!connection.equals("Side3"));  
                    }

                    //Repeat the same process for connections of side 3
                    connection = loader.next();

                    if (connection.equals("No."))
                        loader.next();

                    else
                    {
                        do
                        {
                            model.connect(name, 3, connection);
                            connection = loader.next();
                        } while (!connection.equals("Side4"));  
                    }

                    //Repeat the same process for connections of side 2
                    connection = loader.next();

                    if (connection.equals("No."))
                        loader.next();

                    else
                    {
                        do
                        {
                            model.connect(name, 4, connection);
                            connection = loader.next();
                        } while (!connection.equals("End"));  
                    }
                }

                else if (start.equals("Wire"))
                {
                    //if the start equals wire, store the colour as the next int
                    int c = loader.nextInt();
                    //create a new wire with the next 4 ints and c as the colour
                    createWire(loader.nextInt(), loader.nextInt(), c, loader.nextInt(), loader.nextInt());
                    //set this wire to be highlighted or not with the next boolean value
                    list3.get(list3.size()-1).setHighlighted(loader.nextBoolean());
                    
                    //Store the next word into name
                    String name = loader.next();

                    //while name doesn't equal end, look through the drawings, and if
                    //name quals that drawing's name, connect this wire to draw and loader's next integer
                    while (!name.equals("End"))
                    {
                        Drawing draw = null;
                        for (Drawing d: list)
                        {
                            if (d.getName().equals(name))
                            {
                                draw = d;
                                break;
                            }
                        }
                        list3.get(list3.size()-1).setConnection(draw, loader.nextInt());
                        name = loader.next();
                    }
                }
  
                else if (start.equals("Connections2"))
                {
                    //If start equals Connections2, store the next word into name
                    String name = loader.next();
                    
                    //look through the list of wires to find the wire that this name
                    //is refering to
                    Wire wire = null;
                    for (Wire w: list3)
                        if (w.getName().equals(name))
                        {
                            wire = w;
                            break;
                        }

                    //store the next word into connection
                    String connection = loader.next();
                    Wire wire2 = null;

                    //while connection doesnt equal end, look through the list of wires
                    //to find the wire that this connection is refering to and connect
                    //this wire to that wire
                    while (!connection.equals("End"))
                    {
                        for (Wire w: list3)
                            if (w.getName().equals(connection))
                            {
                                wire2 = w;
                                break;
                            }

                        wire.setConnection(wire2);
                        connection = loader.next();
                    }
                }
            }

            //Get rid of all the fake wire and drawings
            boolean flag = true;

            //While something has been deleted
            while (flag)
            {
                //Set the flag to false and look through the list of drawings and
                //if their bounds are exactly 7 and 7, delete the part and set flag 
                //to true
                flag = false;
                for (Drawing d: list)
                    if (d.getBounds().x == 7 && d.getBounds().y == 7)
                    {
                        model.deletePart(d.getName());
                        list.remove(d);
                        flag = true;
                        break;
                    }
            }

            flag = true;

            //Do the same for wires
            while (flag)
            {
                flag = false;
                for (Wire w: list3)
                    if (w.getBounds().x==7 && w.getBounds().y==7 && w.getWidth()==7 && w.getHeight()==7)
                    {
                        deletedIncrement.add(Integer.parseInt(w.getName().substring(4)));
                        list3.remove(w);
                        flag = true;
                        break;
                    }
            }

            //repaint the schematicpanel and cloase the loader
            schematicPanel.repaint();
            loader.close();
        }
    }
    
    /** clears every single part from the schematic
     */ 
    public void clearAll()
    {
        //go through each drawing and wire and remove it from the schematic
        //panel
        for (Drawing d: list)
        {
            schematicPanel.remove(d);
        }
        
        for (Wire w: list3)
        {
            schematicPanel.remove(w);
        }
        
        //repaint the panel and clear the 2 lists. Also set the wireincrement
        //back to 0
        schematicPanel.repaint();
        list.clear();
        list3.clear();
        wireIncrement = 0;
    }   
    
    /**Accessor method to get the number of pic chips on the schematic
     * @return the number of picChips
     */ 
    public int getNumPic()
    {
        return numPic;
    }
    
    /**Accessor method to get the number of hbridge chips on the schematic
     * @return the number of hbridge chips
     */ 
    public int getNumHBridge()
    {
        return numHBridge;
    }
    
    /** Sorts the arraylist of drawings and wires by getting their names
     * and placing them in ascending order based on their name increments. 
     * Using insertion sort method.
     */ 
    public void sort()
    {
        //Convert the arraylist into an array
        Drawing[] drawing = list.toArray(new Drawing[list.size()]);
        
        Drawing item;   //item to insert
        int pos;        //position of insertion
        boolean flag;   //flag to tell whether insertion location has been found
        int i1, i2;     //the integer values alone without the text for the drawing's name

        for(int counter=1; counter<drawing.length; counter++)
        {
            flag = false;
            //set the item equal to the drawing at counter
            item = drawing[counter];
            //set the position to the counter-1
            pos = counter-1;   
            //set i1 to equal to the first drawing's name without the letters
            i1 = Integer.parseInt(item.getName().replaceAll("\\D+", ""));
            
            //while the position is greater or equal to 0 and insertion location has
            //not been found, make i2 equal to the drwaing at location pos name without the letters
            while((pos>=0) && !flag)
            {
                i2 = Integer.parseInt(drawing[pos].getName().replaceAll("\\D+", ""));
                //if i1 is less than i2, then store the drawing[pos] into drawing [pos+1] 
                //and keep searching by decrementing pos by 1
                if(i1 < i2)
                {
                    drawing[pos+1] = drawing[pos];
                    pos--;
                    
                    //if pos is less than 0, store the item in position 0 of the drawing array
                    if(pos < 0)
                    {
                      drawing[0] = item;
                    }
                }
                
                else
                {
                    //otherwise, insertion location has been found and store
                    //the item into drawing at pos+1
                    flag = true;
                    drawing[pos+1] = item;
                }
           }
        }
        
        //clear the list and make it equal to the sorted array
        list.clear();
        list = new ArrayList<Drawing> (Arrays.asList(drawing));
        
        
        //Do same thing for the wires... I don't want to comment this because i need to sleep
        //Date last modified: January 21, 4:28am
        Wire[] wire = list3.toArray(new Wire[list3.size()]);
        
        Wire item2;
        
        for(int counter=1; counter<wire.length; counter++)
        {
            flag = false;
            item2 = wire[counter];
            pos = counter-1;      
            i1 = Integer.parseInt(item2.getName().substring(4));
            while((pos>=0) && !flag)
            {           
                i2 = Integer.parseInt(wire[pos].getName().substring(4));
                if(i1 < i2)
                {
                    wire[pos+1] = wire[pos];
                    pos--;
                    
                    if(pos < 0)
                    {
                        wire[0] = item2;
                    }
                }
                
                else
                {
                    flag = true;
                    wire[pos+1] = item2;
                }
           }
        }
        
        list3.clear();
        list3 = new ArrayList<Wire> (Arrays.asList(wire));
    }
}

