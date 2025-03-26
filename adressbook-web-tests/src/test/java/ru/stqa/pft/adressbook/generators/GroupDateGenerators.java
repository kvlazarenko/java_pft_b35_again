package ru.stqa.pft.adressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.adressbook.model.GroupDate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDateGenerators {

	@Parameter(names = "-c", description = "Group count")
	public int count;
	@Parameter(names = "-f", description = "Target file")
	public String file;

	public static void main(String[] args) throws IOException {
		GroupDateGenerators generator = new GroupDateGenerators();
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
		List<GroupDate> groups = generateGroups(count);
		save(groups, new File(file));
	}

	private void save(List<GroupDate> groups, File file) throws IOException {
		System.out.println(new File(".").getAbsolutePath());
		Writer writer = new FileWriter(file);
		for (GroupDate group : groups) {
			writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
		}
		writer.close();
	}

	private List<GroupDate> generateGroups(int count) {
		List<GroupDate> groups = new ArrayList<GroupDate>();
		for (int i = 0; i < count; i++) {
			groups.add(new GroupDate().withName(String.format("test" + "%s", i))
							.withHeader(String.format("header" + "%s", i)).withFooter(String.format("footer" + "%s", 1)));
		}
		return groups;
	}
}
