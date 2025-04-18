package ru.stqa.pft.adressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupDate;
import ru.stqa.pft.adressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

	@DataProvider
	public Iterator<Object[]> validGroupsFromXml() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
			String xml = "";
			String line = reader.readLine();
			while (line != null) {
				xml += line;
				line = reader.readLine();
			}
			XStream xstream = new XStream();
			xstream.processAnnotations(GroupDate.class);
			List<GroupDate> groups = (List<GroupDate>) xstream.fromXML(xml);
			return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
		}
	}

	@DataProvider
	public Iterator<Object[]> validGroupsFromJson() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
			String json = "";
			String line = reader.readLine();
			while (line != null) {
				json += line;
				line = reader.readLine();
			}
			Gson gson = new Gson();
			List<GroupDate> groups = gson.fromJson(json, new TypeToken<List<GroupDate>>() {
			}.getType()); //List<GroupDate>.class
			return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
		}
	}

	@Test(dataProvider = "validGroupsFromJson")
	public void testGroupCreation(GroupDate group) throws Exception {
		Groups before = app.db().groups();
		app.goTo().groupPage();
		app.group().create(group);
		assertThat(app.group().count(), equalTo(before.size() + 1));
		Groups after = app.db().groups();
		assertThat(after, equalTo((before.withAdded(
						group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())))));
		verifyGroupListInUI();
	}
}
