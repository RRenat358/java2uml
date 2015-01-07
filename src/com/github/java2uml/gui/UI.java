package com.github.java2uml.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class UI implements ExceptionListener {
    private JFrame mainFrame;
    private JPanel panelForButtons, panelForGeneratedCode, panelForPath, panelForPathAndButtons, panelForDiagram, panelForProgressBarAndCancel, panelForClearAndCopyToClipboard;
    private JButton browse, generatePlantUML, copyToClipboard, saveDiagram, cancelLoading, clearCode;
    private JTabbedPane tabs;
    private JMenuBar menu;
    private JMenu file, help, typeOfDiagramMenu, options, direction, diagramGeneratingMethods, whichRelationsAreShown;
    private JMenuItem helpItem, exitItem, aboutItem, generateItem, chooseItem, saveItem;
    JCheckBoxMenuItem horizontalDirectionCheckboxItem, verticalDirectionCheckboxItem, classDiagramCheckboxItem,
            sequenceDiagramCheckboxItem, reflectionCheckboxItem, parsingCheckboxItem, showLollipops, showHeader, showAssociation,
    showComposition, showAggregation;
    ButtonGroup directionGroup;
    ButtonGroup typeOfDiagramGroup;

    private static Help helpWindow;
    private JTextArea generatedCode;
    private JProgressBar progressBar;
    private JSeparator separatorBetweenPathAndButtons, separatorBetweenButtonsAndProgressBar;
    private JFileChooser fileChooser;
    private File chosenDirectory;
    private BufferedImage diagram;
    private JLabel labelForDiagram;

    ButtonGroup parsingMethod;

    private JScrollPane scrollPane, scrollPaneForDiagram;

    private JTextField path;

    ResourceBundle localeLabels;

    public static final String VERTICAL_DIRECTION = "Vertical";
    public static final String HORIZONTAL_DIRECTION = "Horizontal";
    public static final String CLASS_DIAGRAM = "Class Dia";
    public static final String SEQUENCE_DIAGRAM = "Sequence Dia";




    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }



    public JTextArea getGeneratedCode() {
        return generatedCode;
    }
    public JTextField getPath() {
        return path;
    }

    public JButton getGeneratePlantUML() {
        return generatePlantUML;
    }

    private static class UIHolder {
        static final UI UI_INSTANCE = new UI();
    }

    private UI(){

    }

    public static UI getInstance(){
        return UIHolder.UI_INSTANCE;
    }

    public JCheckBoxMenuItem getHorizontalDirectionCheckboxItem() {
        return horizontalDirectionCheckboxItem;
    }

    public JCheckBoxMenuItem getVerticalDirectionCheckboxItem() {
        return verticalDirectionCheckboxItem;
    }

    public JCheckBoxMenuItem getClassDiagramCheckboxItem() {
        return classDiagramCheckboxItem;
    }

    public JCheckBoxMenuItem getSequenceDiagramCheckboxItem() {
        return sequenceDiagramCheckboxItem;
    }

    public JCheckBoxMenuItem getReflectionCheckboxItem() {
        return reflectionCheckboxItem;
    }

    public JCheckBoxMenuItem getParsingCheckboxItem() {
        return parsingCheckboxItem;
    }

    public JCheckBoxMenuItem getShowLollipops() {
        return showLollipops;
    }

    public JCheckBoxMenuItem getShowHeader() {
        return showHeader;
    }

    public JCheckBoxMenuItem getShowAssociation() {
        return showAssociation;
    }

    public JCheckBoxMenuItem getShowComposition() {
        return showComposition;
    }

    public JCheckBoxMenuItem getShowAggregation() {
        return showAggregation;
    }

    public JMenuItem getGenerateItem() {
        return generateItem;
    }

    public void setGenerateItem(JMenuItem generateItem) {
        this.generateItem = generateItem;
    }



    public void settingStateForAllOptions(){

        classDiagramCheckboxItem.setState(true);
        verticalDirectionCheckboxItem.setState(true);
        classDiagramCheckboxItem.setEnabled(false);
        sequenceDiagramCheckboxItem.setEnabled(false);
        classDiagramCheckboxItem.setState(true);
        reflectionCheckboxItem.setState(true);
        showAggregation.setState(true);
        showAssociation.setState(true);
        showComposition.setState(true);
        showLollipops.setState(true);





    }

    public JButton getCancelLoading() {
        return cancelLoading;
    }

    public void setCancelLoading(JButton cancelLoading) {
        this.cancelLoading = cancelLoading;
    }

    public void settingLocaleLabels(){
        localeLabels = ResourceBundle.getBundle("GUILabels", new Locale("ru"));

    }

    private JMenuBar initMenu(){
        menu = new JMenuBar();

        file = new JMenu(localeLabels.getString("fileMenuLabel"));
        help = new JMenu(localeLabels.getString("helpMenuLabel"));
        typeOfDiagramMenu = new JMenu(localeLabels.getString("typeOfDiagramMenuLabel"));

        options = new JMenu(localeLabels.getString("optionsMenuLabel"));
        direction = new JMenu(localeLabels.getString("directionWillBeMenuLabel"));
        diagramGeneratingMethods = new JMenu(localeLabels.getString("iWantToParseMenuLabel"));
        whichRelationsAreShown = new JMenu(localeLabels.getString("relationsMenuLabel"));

        showAssociation = new StayOpenCheckBoxMenuItem(localeLabels.getString("associationMenuLabel"));
        showAssociation.setState(true);
        showAggregation = new StayOpenCheckBoxMenuItem(localeLabels.getString("aggregationMenuLabel"));
        showComposition = new StayOpenCheckBoxMenuItem(localeLabels.getString("compositionMenuLabel"));

        showHeader = new StayOpenCheckBoxMenuItem(localeLabels.getString("chooseHeaderMenuLabel"));
        showLollipops = new StayOpenCheckBoxMenuItem(localeLabels.getString("showLollipopMenuLabel"));

        helpItem = new JMenuItem(localeLabels.getString("helpMenuLabel"));
        exitItem = new JMenuItem(localeLabels.getString("exitMenuLabel"));

        aboutItem = new JMenuItem(localeLabels.getString("aboutMenuLabel"));
        generateItem = new JMenuItem(localeLabels.getString("generateLabel"));
        chooseItem = new JMenuItem(localeLabels.getString("chooseDirLabel"));

        parsingMethod = new ButtonGroup();
        directionGroup = new ButtonGroup();
        typeOfDiagramGroup = new ButtonGroup();

        reflectionCheckboxItem = new StayOpenCheckBoxMenuItem(localeLabels.getString("classFilesMenuLabel"));
        parsingCheckboxItem = new StayOpenCheckBoxMenuItem(localeLabels.getString("javaFilesMenuLabel"));
        parsingMethod.add(reflectionCheckboxItem);
        parsingMethod.add(parsingCheckboxItem);


        horizontalDirectionCheckboxItem = new StayOpenCheckBoxMenuItem(localeLabels.getString("directionHorizontalLabel"));

        verticalDirectionCheckboxItem = new StayOpenCheckBoxMenuItem(localeLabels.getString("directionVerticalLabel"));

        directionGroup.add(horizontalDirectionCheckboxItem);
        directionGroup.add(verticalDirectionCheckboxItem);

        classDiagramCheckboxItem = new StayOpenCheckBoxMenuItem(localeLabels.getString("classDiagramLabel"));
        sequenceDiagramCheckboxItem = new StayOpenCheckBoxMenuItem(localeLabels.getString("sequenceDiagramLabel"));
        typeOfDiagramGroup.add(classDiagramCheckboxItem);
        typeOfDiagramGroup.add(sequenceDiagramCheckboxItem);

        chooseItem.addActionListener(new ChooseFileActionListener());
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(chooseItem);
        file.add(generateItem);

        file.add(exitItem);

        whichRelationsAreShown.add(showAggregation);
        whichRelationsAreShown.add(showAssociation);
        whichRelationsAreShown.add(showComposition);

        options.add(direction);
        options.add(diagramGeneratingMethods);
        options.add(whichRelationsAreShown);
        diagramGeneratingMethods.add(parsingCheckboxItem);
        diagramGeneratingMethods.add(reflectionCheckboxItem);
        direction.add(horizontalDirectionCheckboxItem);
        direction.add(verticalDirectionCheckboxItem);
        options.add(typeOfDiagramMenu);
        typeOfDiagramMenu.add(classDiagramCheckboxItem);
        typeOfDiagramMenu.add(sequenceDiagramCheckboxItem);
        options.add(showHeader);
        options.add(showLollipops);

        help.add(helpItem);
        help.add(aboutItem);

        menu.add(file);
        menu.add(options);
        menu.add(help);

        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!Help.helpIsNull()) {
                    if (!helpWindow.isVisible()) {
                        helpWindow.setVisible(true);
                    } else {
                        helpWindow.toFront();
                        helpWindow.repaint();
                    }
                } else helpWindow = Help.getInstance();
            }
        });


        return menu;
    }

    public JFrame initUI() {
        settingLocaleLabels();
        mainFrame = new JFrame(localeLabels.getString("titleLabel"));
        panelForButtons = new JPanel();
        panelForGeneratedCode = new JPanel();
        panelForDiagram = new JPanel();
        panelForClearAndCopyToClipboard = new JPanel();
        panelForProgressBarAndCancel = new JPanel();
        browse = new JButton(localeLabels.getString("chooseDirLabel"));
        saveDiagram = new JButton(localeLabels.getString("saveMenuLabel"));
        generatePlantUML = new JButton(localeLabels.getString("generateLabel"));
        cancelLoading = new JButton(localeLabels.getString("cancelLabel"));
        labelForDiagram = new JLabel();
        clearCode = new JButton(localeLabels.getString("clearLabel"));

        copyToClipboard = new JButton(localeLabels.getString("copyToClipboardLabel"));

        generatedCode = new JTextArea();
        path = new JTextField();
        panelForPath = new JPanel();
        panelForPathAndButtons = new JPanel();
        progressBar = new JProgressBar();
        progressBar.setBorder(new EmptyBorder(0, 3, 0, 3));
        tabs = new JTabbedPane();
        scrollPaneForDiagram = new JScrollPane(labelForDiagram);
        separatorBetweenPathAndButtons = new JSeparator();
        separatorBetweenButtonsAndProgressBar = new JSeparator();
        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Java archive (.jar)", "jar"));


        progressBar.setStringPainted(true);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);


        clearCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getGeneratedCode().setText("");
            }
        });
        browse.addActionListener(new ChooseFileActionListener());
        copyToClipboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit ().getSystemClipboard ();
                clipboard.setContents (new StringSelection(getGeneratedCode().getText()), null);
            }
        });

        scrollPaneForDiagram.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneForDiagram.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tabs.addTab(localeLabels.getString("plantUMLTabLabel"), panelForGeneratedCode);
        tabs.addTab(localeLabels.getString("diagramTabLabel"), panelForDiagram);

        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);


        panelForPath.setLayout(new GridBagLayout());
        panelForPath.add(browse, new GridBagConstraints(0,0,1,1,0,0.5,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
        panelForPath.add(path, new GridBagConstraints(20,0,4,1,15,0.5,GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,3),0,0));



        panelForButtons.setLayout(new BoxLayout(panelForButtons, BoxLayout.X_AXIS));

        panelForProgressBarAndCancel.setLayout(new BoxLayout(panelForProgressBarAndCancel, BoxLayout.X_AXIS));

        panelForProgressBarAndCancel.add(cancelLoading);
        panelForProgressBarAndCancel.add(progressBar);
        panelForProgressBarAndCancel.add(generatePlantUML);

        panelForPathAndButtons.setLayout(new BoxLayout(panelForPathAndButtons, BoxLayout.Y_AXIS));
        panelForPathAndButtons.setBorder(new EmptyBorder(3, 1, 3, 1));
        panelForPathAndButtons.add(panelForPath);
        panelForPathAndButtons.add(separatorBetweenPathAndButtons);
        panelForPathAndButtons.add(panelForButtons);
        panelForPathAndButtons.add(separatorBetweenButtonsAndProgressBar);
        panelForPathAndButtons.add(panelForProgressBarAndCancel);




        scrollPane = new JScrollPane(generatedCode);

        generatedCode.setLineWrap(true);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelForGeneratedCode.setLayout(new GridBagLayout());
        panelForGeneratedCode.add(scrollPane, new GridBagConstraints(0, 0, 1, 2, 1, 7, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(2,2,2,2), 0, 0));

        panelForClearAndCopyToClipboard.setLayout(new BoxLayout(panelForClearAndCopyToClipboard, BoxLayout.X_AXIS));
        panelForClearAndCopyToClipboard.add(clearCode);
        panelForClearAndCopyToClipboard.add(copyToClipboard);
        panelForGeneratedCode.add(panelForClearAndCopyToClipboard, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(2,2,2,2), 0, 0));
        panelForGeneratedCode.setBorder(new EmptyBorder(0, 5, 5, 5));

        mainFrame.setJMenuBar(initMenu());
        mainFrame.add(tabs);
        mainFrame.add(BorderLayout.NORTH, panelForPathAndButtons);
        mainFrame.setSize(600, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        return mainFrame;
    }


    public int increaseProgressBarForTwenty(){
        int value = progressBar.getValue() + 20;
        int maximum = progressBar.getMaximum();
        if (value > maximum) value = maximum;
        progressBar.setValue(value);

        return value;
    }

    public void setProgressBarComplete(){
        progressBar.setValue(100);
    }

    public JLabel getLabelForDiagram() {
        return labelForDiagram;
    }

    public void setLabelForDiagram(JLabel labelForDiagram) {
        this.labelForDiagram = labelForDiagram;
    }

    public void showDiagram(String diagramName){
        try {
            diagram = ImageIO.read(new File(diagramName));
//            System.out.println(diagram.getWidth() + "" + "" + diagram.getHeight());
//
            labelForDiagram = new JLabel(new ImageIcon(diagram));

            panelForDiagram.removeAll();
            scrollPaneForDiagram.removeAll();
            scrollPaneForDiagram = new JScrollPane(labelForDiagram);
            panelForDiagram.add(scrollPaneForDiagram);

            tabs.removeTabAt(1);


            tabs.addTab("Diagram", scrollPaneForDiagram);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleExceptionAndShowDialog(Exception exception) {

        JOptionPane.showMessageDialog(mainFrame, exception.getMessage(), "It's an error, breathe deeply", JOptionPane.ERROR_MESSAGE);
    }


    @Override
    public void handleExceptionAndDisplayItInCodeArea(Exception exception) {
        StringBuilder stringBuilder = new StringBuilder("We've got an error, breathe deeply, invisible little dwarves are trying to fix it right now... \n Error message:\n\n" + exception.getMessage());
        generatedCode.setText(stringBuilder.toString());
    }

    public class ChooseFileActionListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            getProgressBar().setString("0%");
            getProgressBar().setValue(0);

            int resultOfChoice = fileChooser.showOpenDialog(mainFrame);
            if (resultOfChoice == JFileChooser.APPROVE_OPTION){
                chosenDirectory = new File(fileChooser.getSelectedFile().getPath());
                path.setText(chosenDirectory.toString());


            }
        }
    }

}
