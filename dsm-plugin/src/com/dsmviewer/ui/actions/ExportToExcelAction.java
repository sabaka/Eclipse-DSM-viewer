package com.dsmviewer.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import com.dsmviewer.Activator;
import com.dsmviewer.ui.dsmtable.DsmTableController;

public class ExportToExcelAction extends Action {

    private final DsmTableController dsmTableController;

    public ExportToExcelAction(DsmTableController dsmTableController) {
        this.dsmTableController = dsmTableController;
    }

    @Override
    public void run() {
        /*NatTable table = dsmTableController.getTable();

        NatExporter exporter = new NatExporter(table.getShell());

        IConfigRegistry configRegistry = table.getConfigRegistry();
       // configRegistry.registerConfigAttribute(ILayerExporter.CONFIG_ATTRIBUTE, new ExcelExporter());

        exporter.exportSingleLayer(table.getLayer(), configRegistry);
*/    }

    @Override
    public String getToolTipText() {
        return "Export to Excel spreadsheet (*.xls)";
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return Activator.getImageDescriptorFromRegistry("export_to_excel.png");
    }

}
