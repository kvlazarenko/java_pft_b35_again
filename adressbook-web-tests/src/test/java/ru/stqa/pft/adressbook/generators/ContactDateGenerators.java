package ru.stqa.pft.adressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.adressbook.model.ContactDate;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDateGenerators {

	@Parameter(names = "-c", description = "Contact count")
	public int count;
	@Parameter(names = "-f", description = "Target file")
	public String file;
	@Parameter(names = "-d", description = "Date format")
	public String format;

	public static void main(String[] args) throws IOException {
		ContactDateGenerators generator = new ContactDateGenerators();
		JCommander jcommander = new JCommander(generator);
		try {
			jcommander.parse(args);
		} catch (ParameterException ex) {
			jcommander.usage();
			return;
		}
		generator.run();
	}

	private void run() throws IOException {
		List<ContactDate> contacts = generateContacts(count);
		format.equals("xml");
		saveAsXml(contacts, new File(file));
	}

	private void saveAsXml(List<ContactDate> contacts, File file) throws IOException {
		XStream xstream = new XStream();
		xstream.processAnnotations(ContactDate.class);
		String xml = xstream.toXML(contacts);
		Writer writer = new FileWriter(file);
		writer.write(xml);
		writer.close();
	}

	private List<ContactDate> generateContacts(int count) {
		List<ContactDate> contacts = new ArrayList<ContactDate>();
		for (int i = 0; i < count; i++) {
			contacts.add(new ContactDate()
							.withFirstname(String.format("firstname" + "%s", i))
							.withLastname(String.format("lastname" + "%s", i))
							.withAddress(String.format("Бульвар Олимпийский" + "%s", i))
							.withHomePhone(String.format("homephone" + "%s", i))
							.withMobilePhone(String.format("mobilephone" + "%s", i))
							.withWorkPhone(String.format("workphone" + "%s", i))
							.withEmail(String.format("e-mail1" + "%s", i))
							.withEmail2(String.format("e-mail12" + "%s", i))
							.withEmail3(String.format("e-mail13" + "%s", i)));
		}
		return contacts;
	}
}
