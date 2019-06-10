package net.tcheltsou.springmvclearning.web.view;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class UsersPdfView extends AbstractPdfView {
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
									Document document, PdfWriter writer,
									HttpServletRequest request, HttpServletResponse response) throws Exception {


		Table table = new Table(2);
		int i = 1;
		for (String header: (String[]) model.get("acceptHeader")) {
			table.addCell("UserAccount " + i++);
			table.addCell(header);
		}
		document.add(table);
	}
}
