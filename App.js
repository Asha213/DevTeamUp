import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [projects, setProjects] = useState([]);
  const [profiles, setProfiles] = useState([]);
  const [token, setToken] = useState('');
  const [profileForm, setProfileForm] = useState({
    bio: '',
    skills: '',
    githubUrl: '',
    linkedinUrl: '',
    experience: '',
    hireMe: false
  });

  useEffect(() => {
    // Login to get JWT token
    axios.post('http://localhost:8080/api/auth/login', {
      username: 'dev1',
      password: 'dev123'
    }).then(response => {
      const jwtToken = response.data;
      setToken(jwtToken);
      // Fetch projects
      axios.get('http://localhost:8080/api/projects', {
        headers: { Authorization: `Bearer ${jwtToken}` }
      }).then(res => setProjects(res.data));
      // Fetch profiles
      axios.get('http://localhost:8080/api/profiles', {
        headers: { Authorization: `Bearer ${jwtToken}` }
      }).then(res => setProfiles(res.data));
    }).catch(error => {
      console.error('Login failed:', error);
    });
  }, []);

  const handleProfileFormChange = (e) => {
    const { name, value, type, checked } = e.target;
    setProfileForm(prev => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value
    }));
  };

  const handleProfileSubmit = (e) => {
    e.preventDefault();
    const profileData = {
      user: { id: 1 }, // Replace with actual user ID
      bio: profileForm.bio,
      skills: profileForm.skills.split(',').map(skill => skill.trim()),
      githubUrl: profileForm.githubUrl,
      linkedinUrl: profileForm.linkedinUrl,
      experience: profileForm.experience,
      hireMe: profileForm.hireMe
    };
    axios.post('http://localhost:8080/api/profiles', profileData, {
      headers: { Authorization: `Bearer ${token}` }
    }).then(response => {
      setProfiles([...profiles, response.data]);
      setProfileForm({
        bio: '',
        skills: '',
        githubUrl: '',
        linkedinUrl: '',
        experience: '',
        hireMe: false
      });
    }).catch(error => {
      console.error('Profile creation failed:', error);
    });
  };

  return (
    <div className="p-4">
      <h1 className="text-3xl font-bold mb-4">DevTeamUp</h1>

      <h2 className="text-xl mb-2">Projects</h2>
      <ul className="list-disc pl-5 mb-6">
        {projects.map(project => (
          <li key={project.id}>
            {project.title} ({project.status}) - {project.techStack.join(', ')}
          </li>
        ))}
      </ul>

      <h2 className="text-xl mb-2">Profiles</h2>
      <ul className="list-disc pl-5 mb-6">
        {profiles.map(profile => (
          <li key={profile.id}>
            {profile.bio} - Skills: {profile.skills.join(', ')} - 
            <a href={profile.githubUrl} className="text-blue-500"> GitHub</a>, 
            <a href={profile.linkedinUrl} className="text-blue-500"> LinkedIn</a>
          </li>
        ))}
      </ul>

      <h2 className="text-xl mb-2">Create Profile</h2>
      <form onSubmit={handleProfileSubmit} className="space-y-4">
        <div>
          <label className="block">Bio:</label>
          <textarea
            name="bio"
            value={profileForm.bio}
            onChange={handleProfileFormChange}
            className="w-full p-2 border rounded"
            required
          />
        </div>
        <div>
          <label className="block">Skills (comma-separated):</label>
          <input
            type="text"
            name="skills"
            value={profileForm.skills}
            onChange={handleProfileFormChange}
            className="w-full p-2 border rounded"
            placeholder="Java, React, Spring Boot"
            required
          />
        </div>
        <div>
          <label className="block">GitHub URL:</label>
          <input
            type="url"
            name="githubUrl"
            value={profileForm.githubUrl}
            onChange={handleProfileFormChange}
            className="w-full p-2 border rounded"
            placeholder="https://github.com/username"
          />
        </div>
        <div>
          <label className="block">LinkedIn URL:</label>
          <input
            type="url"
            name="linkedinUrl"
            value={profileForm.linkedinUrl}
            onChange={handleProfileFormChange}
            className="w-full p-2 border rounded"
            placeholder="https://linkedin.com/in/username"
          />
        </div>
        <div>
          <label className="block">Experience:</label>
          <textarea
            name="experience"
            value={profileForm.experience}
            onChange={handleProfileFormChange}
            className="w-full p-2 border rounded"
          />
        </div>
        <div>
          <label className="flex items-center">
            <input
              type="checkbox"
              name="hireMe"
              checked={profileForm.hireMe}
              onChange={handleProfileFormChange}
              className="mr-2"
            />
            Available for Hire
          </label>
        </div>
        <button type="submit" className="bg-blue-500 text-white p-2 rounded hover:bg-blue-600">
          Create Profile
        </button>
      </form>
    </div>
  );
}

export default App;