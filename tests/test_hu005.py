"""Pruebas de estructura para HU-005 — módulo communication."""

from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent


def test_hu005_documentation_exists():
    doc = ROOT / "docs" / "hu" / "HU-005-comunicacion-padre-profesor.md"
    assert doc.is_file(), "Falta la documentación de HU-005"
    content = doc.read_text(encoding="utf-8")
    assert "HU-005" in content
    assert "communication" in content


def test_communication_module_readme_exists():
    readme = ROOT / "src" / "communication" / "README.md"
    assert readme.is_file(), "Falta README del módulo communication"
    content = readme.read_text(encoding="utf-8")
    assert "SendMessage" in content
    assert "ListConversations" in content
