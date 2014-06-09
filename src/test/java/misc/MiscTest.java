package misc;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter1.Track;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author tanabe
 */
public class MiscTest {

  @Test
  public void flatMapTest() throws Exception {
    // Setup
    // Exercise
    List<Integer> together = Stream.of(asList(1, 2), asList(3, 4))
      .flatMap(numbers -> numbers.stream())
      .collect(toList());

    // Verify
    assertThat(together, is(asList(1, 2, 3, 4)));
  }

  @Test
  public void Chapter3RefactorStep3Anatomy() throws Exception {
    // Setup
    List<Album> albums = unmodifiableList(asList(SampleData.aLoveSupreme, SampleData.sampleShortAlbum));

    // Exercise
    Set<String> tackNames = new HashSet<>();

    List<Track> tracks = albums.stream()
      .flatMap(album -> album.getTracks())
      .collect(toList());

    // Verify
    System.out.println(tracks);
  }
  
  @Test
  public void printMethods() throws Exception {
    // Setup
    Stream<Class<?>> stream = Stream.of(Set.class, List.class, Map.class);
    // Exercise
    // Verify
    stream.flatMap(clazz -> Arrays.stream(clazz.getMethods()))
      .map(method -> method.getName())
      .distinct()
      .sorted()
      .forEach(System.out::println);
  }

  @Test
  public void sentencesSplitterStep0() throws Exception {
    // Setup
    List<String> sentences = asList("I am a Java lover", "Also WildFly lover");
    // Exercise
    // Verify
    sentences.stream()
      .map(s -> s.split(" "))
      .forEach(words -> {
        Arrays.stream(words)
          .forEach(System.out::println);
      });
  }

  @Test
  public void sentencesSplitterStep1() throws Exception {
    // Setup
    List<String> sentences = asList("I am a Java lover", "Also WildFly lover");
    // Exercise
    // Verify
    sentences.stream()
      .flatMap(s -> Arrays.stream(s.split(" ")))
//      .distinct()
      .forEach(System.out::println);
  }

}
