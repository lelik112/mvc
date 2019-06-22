package net.tcheltsou.springmvclearning.web.converter;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import net.tcheltsou.springmvclearning.entity.Ticket;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TicketPdfHttpMessageConverter implements HttpMessageConverter<Ticket> {
	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return false;
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return clazz == Ticket.class && mediaType == MediaType.APPLICATION_PDF;
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return Collections.singletonList(MediaType.APPLICATION_PDF);
	}

	@Override
	public Ticket read(Class<? extends Ticket> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		return null;
	}

	@Override
	public void write(Ticket ticket, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		try {
			Document document = new Document(new Rectangle(500, 500));
			PdfWriter pdfWriter = PdfWriter.getInstance(document, outputMessage.getBody());
			document.open();
			Table table = new Table(2);
			table.addCell(ticket.toString());
			document.add(table);
			document.close();
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}
}
